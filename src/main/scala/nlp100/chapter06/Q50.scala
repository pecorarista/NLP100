package nlp100.chapter06

import java.io.PrintWriter
import scala.io.Source

object Q50 extends App {

  val p = new PrintWriter("src/main/resources/Q50.txt")
  Source.fromURL(getClass.getResource("/nlp.txt"))
    .getLines
    .filter(_ != "")
    .flatMap(_.split("""(?<=[.;:?!])\s(?=[A-Z])"""))
    .foreach(p.println(_))
  p.close

}
