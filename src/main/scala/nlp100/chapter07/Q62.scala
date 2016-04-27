package nlp100.chapter07

import com.redis._
import com.typesafe.config._
import scala.Console

object Q62 extends App {

  Q60.prepare

  println("Counting...")
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
  println(
    """The number of artists whose "area" is Japan amounts to """ +
      Console.BOLD + Console.BLUE + n + Console.RESET + "."
  )

}
