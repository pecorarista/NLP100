package nlp100.chapter02

import scala.io.Source

object Q19 extends App {

  Source.fromURL(getClass.getResource("/hightemp.txt"))
    .getLines
    .map(l => l.split("\t") match { case Array(col1, _, _, _) => col1 })
    .toList
    .groupBy(_.toString)
    .map(t => (t._1, t._2.length))
    .toList
    .sortWith((s, t) => s._2 > t._2)
    .foreach({ case (k, v) => println(s"${v} ${k}") })

}
