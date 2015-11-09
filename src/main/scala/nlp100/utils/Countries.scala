package nlp100.utils

import play.api.libs.json._
import scala.io.Source

object Countries {

  case class Country(text: String, title: String)
  object Country { implicit val countryFormat = Json.format[Country] }

  def uk(): String =
    Source.fromURL(getClass.getResource("/jawiki-country.json"))
      .getLines
      .map(Json.parse(_).as[Country])
      .filter(c => c.title == "イギリス")
      .next
      .text

  def ukInfo(filter: String => String): Map[String, String] =
    uk.split(System.lineSeparator)
      .dropWhile(_ == "{{基礎情報 国")
      .takeWhile(_ != "}}")
      .flatMap(
        l => """\|([^=]+)=(.*)""".r.findAllIn(l).matchData.map(
          m => (filter(m.group(1).trim), filter(m.group(2).trim))
        )
      ).toMap
}
