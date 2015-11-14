package nlp100.chapter05

import nlp100.utils.Neko._

object Q40 extends App {

  morphs(CaboCha).apply(2).foreach(println(_))

}
