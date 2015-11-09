package nlp100.chapter07

import com.redis._
import play.api.libs.json.Json.{stringify, toJson}
import scala.io.StdIn
import scala.Console
import com.typesafe.config._

import nlp100.utils.Artists._

object Q63 extends App {

  val client = new RedisClient("localhost", ConfigFactory.load.getInt("redis.port"))
  client.flushall

  println("Preparing data...")
  artists.foreach(
    artist => artist.tags match {
      case Some(ts) => {
        if(ts.length > 0)
          client.set(artist.name, stringify(toJson(ts)))
      }
      case _ => ()
    }
  )
  println("Done")

  while(true) {
    print("name: ")
    client.get(StdIn.readLine) match {
      case Some(t) =>
        println(Console.BOLD + Console.BLUE + s"tags: ${t}" + Console.RESET)
      case _ =>
        println(Console.BOLD + Console.RED + "Not Found" + Console.RESET)
    }
  }

}
