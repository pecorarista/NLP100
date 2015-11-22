package nlp100.chapter07

import com.typesafe.config.ConfigFactory
import nlp100.utils.Artists._
import reactivemongo.api.MongoDriver
import reactivemongo.bson.BSONDocument
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.io.StdIn
import scala.util.{Failure, Success}

object Q67 extends App {

  val driver = new MongoDriver
  val connection = driver.connection(List("localhost"))
  val database = connection(ConfigFactory.load.getString("mongodb.database"))
  val collection = database.collection("artists")

  print("aliases.name: ")
  while(true) {
    val alias = StdIn.readLine
    collection.find(BSONDocument("aliases.name" -> alias))
      .cursor[Artist]().collect[List]() onComplete {
      case Failure(e) => throw e
      case Success(s) => {
        if(s.length > 0)
          s.foreach(println(_))
        else
          println("Not Found")

        print("aliases.name: ")
      }
    }
  }

}
