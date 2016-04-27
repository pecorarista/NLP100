package nlp100.chapter05

import nlp100.utils.Neko._

object Q43 extends App {

  dependencies.foreach(
    cs => cs.zipWithIndex.foreach({
      case (c, i) => {
        if (c.dst != -1 && c.toText != "" &&
          c.morphs.exists(_.pos == "名詞") && cs(c.dst).morphs.exists(_.pos == "動詞")) {
          println(s"${c.toText}\t${cs(c.dst).toText}")
        }
      }
    })
  )

}
