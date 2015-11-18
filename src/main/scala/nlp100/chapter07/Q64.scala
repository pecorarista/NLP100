package nlp100.chapter07

import com.typesafe.config.ConfigFactory
import reactivemongo.api.MongoDriver
import scala.util.{Failure, Success}
import scala.concurrent.{Future, Await}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import reactivemongo.bson.BSONDocument
import reactivemongo.api.commands.WriteResult
import nlp100.utils.Artists.artists

object Q64 extends App {

  val driver = new MongoDriver
  val connection = driver.connection(List("localhost"))
  val database = connection(ConfigFactory.load.getString("mongodb.database"))
  val collection = database.collection("artists")

  Await.result(collection.remove(BSONDocument.empty), Duration.Inf)
  println("Inserting...")
  val f = Future.sequence(artists.map(artist => collection.insert(artist)))
  val rs = Await.result(f, Duration.Inf)
  println(s"""Successfully ${rs.length} documents have been inserted.""")

}
