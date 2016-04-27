package nlp100.chapter03

import nlp100.utils.Countries.ukInfo

object Q27 extends App {

  val filter =
    ((s: String) => """'{5}(.*?)'{5}""".r.replaceAllIn(s, "$1"))
      .andThen(s => """'{2,3}(.*?)'{2,3}""".r.replaceAllIn(s, "$1"))
      .andThen(s => """\[\[([^\|]+?)\]\]""".r.replaceAllIn(s, "$1"))
      .andThen(s => """\[\[(?!ファイル:)([^\|]+?)\|(?:.*?)\]\]""".r.replaceAllIn(s, "$1"))

  ukInfo(filter = filter).foreach(println(_))

}
