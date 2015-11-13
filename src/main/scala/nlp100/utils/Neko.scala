package nlp100.utils

import scala.collection.mutable.Queue
import scala.collection.mutable.Map
import scala.io.Source

object Neko {
  case class Morph(
    surface: String,
    base: String,
    pos: String,
    pos1: String
  )

  case class Chunk(
    morphs: List[Morph],
    dst: Int,
    srcs: List[Int]
  )

  type Sentence = List[Chunk]

  def morphs(): List[List[Morph]] = {
    var ms = Queue.empty[Morph]
    var mss = Queue.empty[List[Morph]]

    for(l <- Source.fromURL(getClass.getResource("/neko.txt.cabocha")).getLines) {
      if(l.head != '*'){
        if(l == "EOS") {
            mss.enqueue(ms.toList)
            ms = Queue.empty[Morph]
        }
        else {
          val m = l.split("\t") match {
            case Array(surface, description) => {
              description.split(",") match {
                case Array(pos, pos1, _, _, _, _, _) =>
                  Morph(surface, "*", pos, pos1)
                case Array(pos, pos1, _, _, _, _, base, _, _) =>
                  Morph(surface, base, pos, pos1)
              }
            }
          }
          ms.enqueue(m)
        }
      }
    }
    mss.toList
  }

  def dependencies(): List[Sentence] = {
    var deps = Map.empty[Int, Int]
    var ms = Queue.empty[Morph]
    var mss = Queue.empty[Queue[Morph]]
    var ss = Queue.empty[Sentence]

    for(l <- Source.fromURL(getClass.getResource("/neko.txt.cabocha")).getLines) {
      if(l.head == '*') {
        val (src, dst) = l.split(" ") match {
          case Array(_, i, j, _, _) => (i.toInt, j.dropRight(1).toInt)
        }
        deps += src -> dst
        if(ms.nonEmpty) {
          mss.enqueue(ms)
          ms = Queue.empty[Morph]
        }
      }
      else if(l == "EOS") {
        mss.enqueue(ms)
        val s = mss.zipWithIndex.map({
          case (ms, i) => {
            Chunk(ms.toList, deps(i), deps.filter({ case (_, v) => v == i }).keys.toList)
          }
        }).toList
        ss.enqueue(s)
        deps = Map.empty[Int, Int]
        ms = Queue.empty[Morph]
        mss = Queue.empty[Queue[Morph]]
      }
      else {
        val m = l.split("\t") match {
          case Array(surface, description) => {
            description.split(",") match {
              case Array(pos, pos1, _, _, _, _, _) =>
                Morph(surface, "*", pos, pos1)
              case Array(pos, pos1, _, _, _, _, base, _, _) =>
                Morph(surface, base, pos, pos1)
            }
          }
        }
        ms.enqueue(m)
      }
    }
    ss.toList
  }

}
