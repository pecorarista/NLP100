package nlp100.chapter03

import nlp100.utils.Countries.uk

object Q26 extends App {

  val pattern = """\|([^=]+)=(.*)""".r
  val f =
    ((s: String) =>"""'{5}(.*?)'{5}""".r.replaceAllIn(s, "$1"))
    .andThen(s =>"""'{2,3}(.*?)'{2,3}""".r.replaceAllIn(s, "$1"))

  uk.split("\n")
    .dropWhile(_ == "{{基礎情報 国")
    .takeWhile(_ != "}}")
    .flatMap(
      l => pattern.findAllIn(l).matchData.map(
        m => (f(m.group(1).trim), f(m.group(2).trim))
      ).toMap
    ).foreach(println(_))


}
