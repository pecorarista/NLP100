package nlp100.chapter04

import scala.collection.mutable.Queue
import scala.io.Source

import nlp100.utils.Neko.Morph

object Q30 {

  def morphs(): List[List[Morph]] = {
    var ms = Queue[Morph]()
    var mss = Queue[List[Morph]]()

    for(l <- Source.fromURL(getClass.getResource("/neko.txt.mecab")).getLines) {
      if(l.head != '*'){
        l match {
          case "EOS" =>
            mss.enqueue(ms.toList)
            ms = Queue[Morph]()
          case _ => {
            val m = l.split("\t") match {
              case Array(surface, description) => {
                description.split(",") match {
                  case Array(pos, pos1, _, _, _, _, _) =>
                    Morph(surface, "*", pos, pos1)
                  case Array(pos, pos1, _, _, _, _, base, _, _) =>
                    Morph(surface , base, pos, pos1)
                }
              }
            }
            ms.enqueue(m)
          }
        }
      }
    }
    mss.toList
  }

}
