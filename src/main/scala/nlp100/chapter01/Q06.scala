package nlp100.chapter01

import nlp100.utils.Ngram.bigram

object Q06 extends App {

  val X = bigram("paraparaparadise".toList).toSet
  val Y = bigram("paragraph".toList).toSet
  println(X | Y)
  println(X & Y)
  println(X &~ Y)
  println(X.contains(List('s', 'e')) || Y.contains(List('s', 'e')))

}
