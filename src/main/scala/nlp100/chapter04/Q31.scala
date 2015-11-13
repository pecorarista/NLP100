package nlp100.chapter04

object Q31 extends App {

  val s = Q30.morphs
    .flatMap(ms => ms.filter(m => m.pos == "動詞").map(m => m.surface))
    .mkString(", ")

  println(s)

}
