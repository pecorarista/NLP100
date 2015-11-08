package nlp100.chapter07

import com.redis._
import com.typesafe.config._

object Q62 extends App {

  val client = new RedisClient("localhost", ConfigFactory.load.getInt("redis.port"))
  val n =
    client.keys("*") match {
      case Some(l) =>
        l.filter(
          item => item match {
            case Some(key) =>
              client.get(key) match {
                case Some(value) => value == "Japan"
                case _ => false
              }
            case _ => false
          }
        ).length
      case _ => 0
    }
  println(n)

}
