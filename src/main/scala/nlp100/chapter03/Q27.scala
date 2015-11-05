package nlp100.chapter03

import nlp100.utils.Countries.uk

object Q27 extends App {

  val pattern1 = """\|([^=]+)=(.*)""".r
  def f(s: String): String =
    s.replace("""'''''""", "").replace("""'''""", "").replace("""''""", "")
      .replace("[[", "").replace("]]", "")

  uk.split("\n")
    .dropWhile(_ == "{{基礎情報 国")
    .takeWhile(_ != "}}")
    .flatMap(l => pattern1.findAllIn(l).matchData.map(
      m => (m.group(1).trim, f(m.group(2)))
      ).toMap
    ).foreach(println(_))

}
