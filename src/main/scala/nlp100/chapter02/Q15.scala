package nlp100.chapter02

import scala.io.Source

object Q15 {

  def last(n: Int) =
    Source.fromURL(getClass.getResource("/hightemp.txt"))
      .getLines.toList.reverse.take(n).reverse.foreach(println(_))

}
