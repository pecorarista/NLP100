package nlp100.chapter04

import nlp100.chapter04.Q30._

object Q33 extends App {
  val s = morphs
    .flatMap(ms => ms.filter(m => m.pos == "名詞" && m.pos1 == "サ変接続").map(m => m.base))
    .mkString(", ")
  println(s)
}
