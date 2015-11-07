package nlp100.chapter07

import com.redis._
import play.api.libs.json.Json
import scala.io.Source
import com.typesafe.config._

object Q60 extends App {

  val client = new RedisClient("localhost", ConfigFactory.load.getInt("redis.port"))
  client.flushall

  Source.fromURL(getClass.getResource("/artist.json")).getLines.foreach({
    l => {
      val json = Json.parse(l)
      ((json \ "name").asOpt[String], (json \ "area").asOpt[String]) match {
        case (Some(name), Some(area)) => client.set(name, area)
        case _ => ()
      }
    }
  })

}
