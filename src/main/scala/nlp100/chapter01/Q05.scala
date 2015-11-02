package nlp100.chapter01

import nlp100.utils.Ngram._

object Q05 extends App {

  println(bigram("I am an NLPer".toList))
  println(bigram("I am an NLPer".split(" ").toList))

}
