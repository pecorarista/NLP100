package nlp100.chapter03

import nlp100.utils.Countries.uk

object Q22 extends App {

  val pattern = """\[\[Category:([^\|]*)\|?.*\]\]""".r

  uk.split(System.lineSeparator)
    .foreach(l => pattern.findAllIn(l).matchData.foreach(m => println(m.group(1).trim)))

}
