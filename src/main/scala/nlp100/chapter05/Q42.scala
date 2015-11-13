package nlp100.chapter05

import nlp100.utils.Neko._

object Q42 extends App {

  def f(c: Chunk): String =
    c.morphs.map(_.surface)
      .filter(! _.matches("[、，。．　]")).mkString

  dependencies.foreach(
    s => s.zipWithIndex.foreach({
      case (c, i) => {
        if(c.dst != -1 && f(c) != "") {
          println(s"${f(c)}\t${f(s.apply(c.dst))}")
        }
      }
    })
  )

}
