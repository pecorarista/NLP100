package nlp100.chapter07

import scala.util.{Failure, Success}
import reactivemongo.api.MongoDriver
import scala.concurrent.ExecutionContext.Implicits.global
import com.typesafe.config.ConfigFactory
import reactivemongo.bson.BSONDocument
import play.api.libs.json.Json
import nlp100.utils.Artists.artists

object Q64 extends App {

  val driver = new MongoDriver
  val connection = driver.connection(List("localhost"))
  val database = connection(ConfigFactory.load.getString("mongodb.database"))
  val collection = database.collection("artists")

  val documents = artists.toSeq.map(implicitly[collection.ImplicitlyDocumentProducer](_))

  collection.remove(BSONDocument.empty).onComplete {
    case Failure(e) => throw e
    case Success(_) => {
      println("Inserting...")
      collection.bulkInsert(ordered = true)(documents: _*).onComplete {
        case Failure(e) => throw e
        case Success(result) =>
          println(s"""Successfully ${result.n} documents have been inserted into collection "${collection.name}".""")
      }
    }
  }

}
