package nlp100.chapter07

import com.typesafe.config.ConfigFactory
import reactivemongo.api.MongoDriver
import scala.util.{Failure, Success}
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import reactivemongo.bson.BSONDocument
import scala.io.StdIn
import nlp100.utils.Artists._

object Q66 extends App {

  val driver = new MongoDriver
  val connection = driver.connection(List("localhost"))
  val database = connection(ConfigFactory.load.getString("mongodb.database"))
  val collection = database.collection("artists")

  collection.find(BSONDocument("area" -> "Japan"))
    .cursor[Artist]().collect[List]() onComplete {
    case Failure(e) => throw e
    case Success(s) => {
      if(s.length > 0)
        s.foreach(println(_))
      else
        println("Not Found")
    }
  }

}
