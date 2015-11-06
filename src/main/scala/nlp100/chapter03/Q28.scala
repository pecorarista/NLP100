package nlp100.chapter03

import nlp100.utils.Countries.ukInfo

object Q28 extends App {

  val filter = Q26.filter
    .andThen(s =>"""\[\[([^\|]+?)\]\]""".r.replaceAllIn(s, "$1"))
    .andThen(s =>"""\[\[(?:ファイル:)?([^\|]+?)\|(?:.*?)\]\]""".r.replaceAllIn(s, "$1"))
    .andThen(s =>"""\{\{lang\|..\|(.+?)\}\}""".r.replaceAllIn(s, "$1"))
    .andThen(s =>"""<ref(?:[^>]*?)>(.+?)</ref>""".r.replaceAllIn(s, "$1"))
    .andThen(s =>"""<ref(?:.+?)/>""".r.replaceAllIn(s, ""))
    .andThen(s =>"""<br */>""".r.replaceAllIn(s, ""))

  ukInfo(filter=filter).foreach(println(_))

}
