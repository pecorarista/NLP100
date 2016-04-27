package nlp100.chapter05

import nlp100.utils.Neko._

object Q42 extends App {

  dependencies.foreach(
    cs => cs.zipWithIndex.foreach({
      case (c, i) => {
        if (c.dst != -1 && c.toText != "") {
          println(s"${c.toText}\t${cs(c.dst).toText}")
        }
      }
    })
  )

}
