package nlp100.chapter07

import com.typesafe.config.ConfigFactory
import reactivemongo.api.MongoDriver
import reactivemongo.bson.BSONDocument
import scala.util.{ Failure, Success }
import scala.concurrent.ExecutionContext.Implicits.global
import scala.Console

object Q66 extends App {

  val driver = new MongoDriver
  val connection = driver.connection(List("localhost"))
  val database = connection(ConfigFactory.load.getString("mongodb.database"))
  val collection = database.collection("artists")

  collection.count(Some(BSONDocument("area" -> "Japan"))) onComplete {
    case Failure(e) => throw e
    case Success(n) => {
      println(Console.BOLD + Console.BLUE + n.toString + Console.RESET)
      driver.close()
    }
  }

}
