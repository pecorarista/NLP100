package nlp100.chapter07

import com.typesafe.config.ConfigFactory
import nlp100.utils.Artists._
import reactivemongo.api.MongoDriver
import reactivemongo.bson.BSONDocument
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{ Failure, Success }

object Q68 extends App {

  val driver = new MongoDriver
  val connection = driver.connection(List("localhost"))
  val database = connection(ConfigFactory.load.getString("mongodb.database"))
  val collection = database.collection("artists")

  collection.find(BSONDocument("tags.value" -> "dance"))
    .sort(BSONDocument("rating.count" -> -1))
    .cursor[Artist]().collect[List]() onComplete {
      case Failure(e) => throw e
      case Success(s) => {
        if (s.length > 0)
          s.take(10) foreach {
            artist =>
              {
                val name = artist.name
                val rating = artist.rating.getOrElse(Rating(0, 0))
                println(s"$name\n  count: ${rating.count}, value: ${rating.value}")
              }
          }
        else
          println("Not Found")

        driver.close()
      }
    }

}
