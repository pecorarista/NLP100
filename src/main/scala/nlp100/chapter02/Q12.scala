package nlp100.chapter02

import java.io.PrintWriter
import scala.io.Source

object Q12 extends App {

  val p1 = new PrintWriter("src/main/resources/col1.txt")
  val p2 = new PrintWriter("src/main/resources/col2.txt")

  Source.fromURL(getClass.getResource("/hightemp.txt")).getLines.foreach(
    l =>
      l.split('\t') match {
        case Array(col1, col2, _, _) => {
          p1.println(col1)
          p2.println(col2)
        }
      }
  )

  p1.close
  p2.close

}
