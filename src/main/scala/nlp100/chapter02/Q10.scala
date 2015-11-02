package nlp100.chapter02

import scala.io.Source

object Q10 extends App {

  println(Source.fromURL(getClass.getResource("/hightemp.txt")).getLines.length)

}
