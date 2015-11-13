package nlp100.chapter05

import nlp100.utils.Neko._

object Q41 extends App {

  dependencies.apply(7).foreach(
    c => println(s"${c.morphs.map(_.surface).mkString}\t${c.dst}")
  )

}
