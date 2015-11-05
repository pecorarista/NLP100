package nlp100.chapter03

import nlp100.utils.Countries.uk

object Q23 extends App {

  val pattern = """^(=+)([^=]*)=+$""".r

  uk.split("\n")
    .foreach(
      l =>
        pattern
          .findAllIn(l)
          .matchData
          .foreach(
            m => {
              val sectionLevel = m.group(1).length - 1
              val sectionName = m.group(2).trim
              println(s"$sectionName ($sectionLevel)")
            }
          )
    )
}
