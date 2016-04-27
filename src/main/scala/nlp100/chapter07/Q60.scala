package nlp100.chapter07

import com.redis._
import com.typesafe.config.ConfigFactory
import play.api.libs.json.Json
import scala.io.Source

object Q60 {

  def prepare(): Unit = {

    val client = new RedisClient("localhost", ConfigFactory.load.getInt("redis.port"))
    client.flushall

    println("Preparing Data...")

    Source.fromURL(getClass.getResource("/artist.json")).getLines.foreach({
      l =>
        {
          val json = Json.parse(l)
          ((json \ "name").asOpt[String], (json \ "area").asOpt[String]) match {
            case (Some(name), Some(area)) => client.set(name, area)
            case _ => ()
          }
        }
    })

    println("Done")

  }

}
