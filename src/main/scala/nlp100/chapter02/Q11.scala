package nlp100.chapter02

import scala.io.Source

object Q11 extends App {

  Source.fromURL(getClass.getResource("/hightemp.txt")).getLines.foreach(println(_))

}
