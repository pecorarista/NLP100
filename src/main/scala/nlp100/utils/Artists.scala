package nlp100.utils

import scala.io.Source
import play.api.libs.json.Json

object Artists {

  // See https://www.playframework.com/documentation/2.3.x/ScalaJsonInception.
  case class Alias(name: String, sort_name: String)
  object Alias { implicit val aliasFormat = Json.format[Alias] }

  case class Date(year: Int, month: Int, date: Int)
  object Date { implicit val beginFormat = Json.format[Date] }

  case class Tag(count: Int, value: String)
  object Tag { implicit val tagFormat = Json.format[Tag] }

  case class Rating(count: Int, value: Int)
  object Rating { implicit val ratingFormat = Json.format[Rating] }

  case class Artist(
    id: Int,
    gid: String,
    name: String,
    sort_name: String,
    area: Option[String],
    aliases: Option[Seq[Alias]],
    begin: Option[Date],
    end: Option[Date],
    tags: Option[Seq[Tag]],
    rating: Option[Seq[Rating]]
  )
  object Artist { implicit val artistFormat = Json.format[Artist] }

  def artists(): Iterator[Artist] =
    Source.fromURL(getClass.getResource("/artist.json"))
      .getLines
      .map(Json.parse(_).asOpt[Artist])
      .flatMap(a => a)

}
