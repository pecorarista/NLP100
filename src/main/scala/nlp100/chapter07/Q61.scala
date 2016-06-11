package nlp100.chapter07

import com.redis._
import scala.io.StdIn
import scala.Console
import com.typesafe.config.ConfigFactory

object Q61 extends App {

  Q60.prepare

  val client = new RedisClient("localhost", ConfigFactory.load.getInt("redis.port"))
  while (true) {
    print("name: ")
    client.get(StdIn.readLine) match {
      case Some(t) =>
        println(Console.BOLD + Console.BLUE + s"area: ${t}" + Console.RESET)
      case _ =>
        println(Console.BOLD + Console.RED + "Not Found" + Console.RESET)
    }
  }

}
