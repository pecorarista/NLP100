package nlp100.utils

import argonaut._, Argonaut._
import scala.io.Source

object Countries {
  case class Country(text: String, title: String)

  implicit def CountryCodecJson: CodecJson[Country] =
    casecodec2(Country.apply, Country.unapply)("text", "title")

  def uk(): String = {
    Source.fromURL(getClass.getResource("/jawiki-country.json"))
      .getLines.map(
        Parse.decodeOption[Country](_) match {
          case Some(country) =>
            if(country.title == "イギリス")
              country.text
            else
              ""
          case _ => ""
        }).filter(_ != "").next
  }

  def ukInfo(filter: String => String): Map[String, String] =
    uk.split("\n")
      .dropWhile(_ == "{{基礎情報 国")
      .takeWhile(_ != "}}")
      .flatMap(
        l => """\|([^=]+)=(.*)""".r.findAllIn(l).matchData.map(
          m => (filter(m.group(1).trim), filter(m.group(2).trim))
        )
      ).toMap
}
