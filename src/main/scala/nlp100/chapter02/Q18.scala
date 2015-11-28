package nlp100.chapter02

import scala.io.Source

object Q18 extends App {

  Source.fromURL(getClass.getResource("/hightemp.txt"))
    .getLines
    .toList
    .sortBy(l => l.split('\t') match { case Array(_, _, col3, _) => col3.toDouble })
    .reverse
    .foreach(println(_))

}
