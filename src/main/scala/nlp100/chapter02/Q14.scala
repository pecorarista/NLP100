package nlp100.chapter02

import scala.io.Source

object Q14 {

  def first(n: Int) =
    Source.fromURL(getClass.getResource("/hightemp.txt"))
      .getLines.toList.take(n).foreach(println(_))

}
