package nlp100.chapter03

import nlp100.utils.Countries.uk

object Q25 extends App {

  val pattern = """\|([^=]+)=(.*)""".r

  uk.split("\n")
    .dropWhile(_ == "{{基礎情報 国")
    .takeWhile(_ != "}}")
    .flatMap(l => pattern.findAllIn(l).matchData.map(
      m => (m.group(1).trim, m.group(2).trim)).toMap[String, String]
    ).foreach(println(_))

}
