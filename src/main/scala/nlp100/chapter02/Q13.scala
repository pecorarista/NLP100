package nlp100.chapter02

import java.io.PrintWriter
import scala.io.Source

object Q13 extends App {

  val p = new PrintWriter("src/main/resources/merged.txt")

  val col1 = Source.fromURL(getClass.getResource("/col1.txt")).getLines.toList
  val col2 = Source.fromURL(getClass.getResource("/col2.txt")).getLines.toList

  col1.zip(col2).foreach({ case (x, y) => p.println(s"${x}\t${y}") })

  p.close

}
