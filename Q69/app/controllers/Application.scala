package controllers

import java.util.concurrent.TimeoutException
import javax.inject.Inject
import models.{ Artist, Rating, Page }
import play.api.Logger
import play.api.i18n.MessagesApi
import play.api.mvc.{ Action, Controller }
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json
import play.api.libs.json.Json.toJsFieldJsValueWrapper
import play.modules.reactivemongo.{
  MongoController, ReactiveMongoApi, ReactiveMongoComponents
}
import play.modules.reactivemongo.json._
import play.modules.reactivemongo.json.collection.JSONCollection
import reactivemongo.bson.BSONDocument
import reactivemongo.api.QueryOpts
import scala.concurrent.{Future, Await}
import scala.concurrent.duration.DurationInt
import scala.math.{ abs, signum }
import scala.util.{ Failure, Success }
import views.html

class Application @Inject() (val reactiveMongoApi: ReactiveMongoApi, val messagesApi: MessagesApi) extends Controller with MongoController with ReactiveMongoComponents {

  implicit val timeout = 10.seconds

  lazy val collection: JSONCollection = db.collection[JSONCollection]("artists")

  import models._

  def index = Action { Home }

  val Home = Redirect(routes.Application.list())

  /**
   * Display the paginated list of artists.
   *
   * @param page Current page number (starts from 0) @param orderBy Column to be sorted @param nameFilter Filter applied on artist names
   * @param aliasFilter Filter applied on artist aliases
   * @param tagFilter Filter applied on artist tags
   */
  def list(page: Int, orderBy: Int, nameFilter: String, aliasFilter: String, tagFilter: String) = Action.async { implicit request =>

    val pageSize = 20
    val offset = page * pageSize

    var j = Json.obj()
    if(nameFilter.length > 0)
      j = j + ("name" -> Json.obj("$regex" -> (".*" + nameFilter + ".*"), "$options" -> "i"))
    else
      ()
    if(aliasFilter.length > 0)
      j = j + ("aliases" -> Json.obj("$elemMatch" -> Json.obj("name" -> Json.obj("$regex" -> (".*" + aliasFilter + ".*"), "$options" -> "i"))))
    else
      ()
    if(tagFilter.length > 0)
      j = j + ("tags" -> Json.obj("$elemMatch" -> Json.obj("value" -> Json.obj("$regex" -> (".*" + tagFilter + ".*"), "$options" -> "i"))))
    else
      ()

    val sort = orderBy match {
      case 1 => ((x: Artist, y: Artist) => x.sort_name < y.sort_name)
      case -1 => ((x: Artist, y: Artist) => x.sort_name > y.sort_name)
      case 2 => ((x: Artist, y: Artist) => x.rating < y.rating)
      case _ => ((x: Artist, y: Artist) => x.rating > y.rating)
    }

    val futurePage: Future[List[Artist]] = collection.find(j)
      .options(QueryOpts(skipN = page * pageSize))
      .cursor[Artist]()
      .collect[List](pageSize)
    val total = Await.result(collection.count(Some(j)), 30.seconds)

    futurePage.map({ artists =>
      implicit val msg = messagesApi.preferred(request)
      Ok(html.list(Page(artists.sortWith((x, y) => sort(x, y)), page, offset, total), orderBy, nameFilter, aliasFilter, tagFilter))
    }).recover {
      case t: TimeoutException => {
        Logger.error("Problem found in artist list process")
        InternalServerError(t.getMessage)
      }
    }
  }

}