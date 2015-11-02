package nlp100.chapter01

import scala.util.Random.shuffle

object Q09 extends App {

  def typoglycemia(s: String): String =
    s.split(" ").map(
      w =>
        if(w.length <= 4)
          w
        else
          w.head + shuffle(w.toList.tail.init).mkString("") + w.last
    ).mkString(" ")

  println(typoglycemia("I couldn't believe that I could actually understand what I was reading : the phenomenal power of the human mind ."))

}
