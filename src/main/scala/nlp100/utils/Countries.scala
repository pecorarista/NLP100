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
}
