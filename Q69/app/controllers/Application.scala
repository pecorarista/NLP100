package controllers

import com.typesafe.config.ConfigFactory
import java.util.concurrent.TimeoutException
import javax.inject.Inject
import models.{ Artist, Page }
import play.api.Logger
import play.api.i18n.MessagesApi
import play.api.mvc.{ Action, Controller }
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json
import play.api.libs.json.Json.toJsFieldJsValueWrapper
import play.modules.reactivemongo.{
  MongoController, ReactiveMongoApi, ReactiveMongoComponents
}

import reactivemongo.play.json._
import reactivemongo.play.json.collection.JSONCollection
import reactivemongo.bson.BSONDocument
import reactivemongo.api.QueryOpts
import scala.concurrent.Future
import scala.concurrent.duration.DurationInt
import scala.math.signum
import scala.util.{ Failure, Success }
import views.html

class Application @Inject() (val reactiveMongoApi: ReactiveMongoApi, val messagesApi: MessagesApi) extends Controller with MongoController with ReactiveMongoComponents {

  implicit val timeout = 10.seconds

  val (dbName, collectionName) = {
    val config = ConfigFactory.load()
    (config.getString("db.name"), config.getString("db.collection"))
  }
  def collection: Future[JSONCollection] = connection.database(dbName).map(_.collection[JSONCollection](collectionName))

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

    var matchQuery = Json.obj()
    if(nameFilter.length > 0)
      matchQuery = matchQuery + ("name" -> Json.obj("$regex" -> (".*" + nameFilter + ".*"), "$options" -> "i"))
    if(aliasFilter.length > 0)
      matchQuery = matchQuery + ("aliases" -> Json.obj("$elemMatch" -> Json.obj("name" -> Json.obj("$regex" -> (".*" + aliasFilter + ".*"), "$options" -> "i"))))
    if(tagFilter.length > 0)
      matchQuery = matchQuery + ("tags" -> Json.obj("$elemMatch" -> Json.obj("value" -> Json.obj("$regex" -> (".*" + tagFilter + ".*"), "$options" -> "i"))))

    val sortQuery = orderBy match {
      case 1|(-1) => Json.obj("name" -> signum(orderBy))
      case _ => Json.obj("rating.value" -> signum(orderBy))
    }

    val futureTotal = collection.flatMap(_.count(Some(matchQuery)))
    val futureArtists = collection.flatMap(_.find(matchQuery).options(QueryOpts(skipN = page * pageSize)).sort(sortQuery).cursor[Artist]().collect[List](pageSize))

    futureTotal.zip(futureArtists).map({
      case (total, artists) => {
        implicit val msg = messagesApi.preferred(request)
        Ok(html.list(Page(artists, page, offset, total), orderBy, nameFilter, aliasFilter, tagFilter))
      }
    }).recover {
      case t: TimeoutException => {
        Logger.error("Problem found in artist list process")
        InternalServerError(t.getMessage)
      }
    }
  }

}
