package nlp100.chapter02

import scala.io.Source

object Q17 extends App {

  Source.fromURL(getClass.getResource("/col1.txt"))
    .getLines
    .toSet[String]
    .foreach(println(_))

}
