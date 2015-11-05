package nlp100.chapter03

import nlp100.utils.Countries.uk

object Q24 extends App {

  val pattern = """ファイル:([^\|]+)""".r

  uk.split("\n")
    .foreach(l => pattern.findAllIn(l).matchData.foreach(m => println(m.group(1).trim)))
}
