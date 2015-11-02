package nlp100.chapter04

import nlp100.chapter04.Q30._

object Q31 extends App {
  val s = morphs
    .flatMap(ms => ms.filter(m => m.pos == "動詞").map(m => m.surface))
    .mkString(", ")
  println(s)
}
