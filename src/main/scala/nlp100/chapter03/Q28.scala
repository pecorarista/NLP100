package nlp100.chapter03

import nlp100.utils.Countries.ukInfo

object Q28 {

  val filter =
    ((s: String) =>"""'{5}(.*?)'{5}""".r.replaceAllIn(s, "$1"))
    .andThen(s =>"""'{2,3}(.*?)'{2,3}""".r.replaceAllIn(s, "$1"))
    .andThen(s =>"""\[\[([^\|]+?)\]\]""".r.replaceAllIn(s, "$1"))
    .andThen(s =>"""\[\[(?:ファイル:)?([^\|]+?)\|(?:.*?)\]\]""".r.replaceAllIn(s, "$1"))
    .andThen(s =>"""\{\{lang\|..\|(.+?)\}\}""".r.replaceAllIn(s, "$1"))
    .andThen(s =>"""<ref(?:[^>]*?)>(.+?)</ref>""".r.replaceAllIn(s, "$1"))
    .andThen(s =>"""<ref(?:.+?)/>""".r.replaceAllIn(s, ""))
    .andThen(s =>"""<br */>""".r.replaceAllIn(s, ""))

  val ukInfoTrimmed = ukInfo(filter=filter)

}
