package nlp100.chapter06

import java.io.PrintWriter
import scala.io.Source

object Q51 extends App {

  val p = new PrintWriter("src/main/resources/Q51.txt")
  Source.fromURL(getClass.getResource("/Q50.txt")).getLines foreach {
    s => {
      s.split(' ').foreach(w => p.println(w))
      p.println("")
    }
  }
  p.close

}
