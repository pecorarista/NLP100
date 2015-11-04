package nlp100.chapter02

import java.io.PrintWriter
import scala.io.Source

object Q17 extends App {

  val cs: Set[Char] =
    Source.fromURL(getClass.getResource("/col1.txt"))
      .getLines
      .foldLeft("")(_ ++ _)
      .toCharArray
      .toSet

  println("""{'""" + cs.mkString("""', '""") + """'}""")

}
