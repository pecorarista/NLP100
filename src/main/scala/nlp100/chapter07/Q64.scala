package nlp100.chapter07

import com.typesafe.config.ConfigFactory
import nlp100.utils.Artists._
import reactivemongo.api.MongoDriver
import reactivemongo.api.commands.WriteResult
import reactivemongo.api.indexes._
import reactivemongo.api.indexes.IndexType.Text
import reactivemongo.bson.BSONDocument
import reactivemongo.core.commands.Count
import scala.concurrent.{Future, Await}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.io.Source
import scala.util.{Failure, Success}

object Q64 extends App {

  val driver = new MongoDriver
  val connection = driver.connection(List("localhost"))
  val database = connection(ConfigFactory.load.getString("mongodb.database"))
  val collection = database.collection("artists")

  Await.result(collection.remove(BSONDocument.empty), Duration.Inf)
  println("Inserting...")
  val f = Future.sequence(artists.map(artist => collection.insert(artist)))
  val rs = Await.result(f, Duration.Inf)
  println(s"""In total ${rs.length} documents have been inserted.""")

  val indexName = "nlp100.chapter07.Q64"
  collection.indexesManager.ensure(
    Index(
      key = Seq("name" -> Text, "aliases.name" -> Text, "tags.value" -> Text, "rating.value" -> Text),
      name = Some(indexName)
    )
  ) onComplete {
      case Failure(e) => throw e
      case Success(b) => {
        println(if(b) s"""Index "$indexName" was created.""" else s"""Index "$indexName" already exists.""")
        driver.close()
      }
  }

}
