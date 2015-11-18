package nlp100.utils

import scala.io.Source
import play.api.libs.json._
import play.api.libs.functional.syntax._
import reactivemongo.bson.Macros

object Artists {

  case class Alias(name: String, sort_name: String)
  object Alias {
    implicit val aliasFormat = Json.format[Alias]
    implicit val aliasHandler = Macros.handler[Alias]
  }

  case class Date(year: Int, month: Int, date: Int)
  object Date {
    implicit val dateFormat = Json.format[Date]
    implicit val dateHandler = Macros.handler[Date]
  }

  case class Tag(count: Int, value: String)
  object Tag {
    implicit val tagFormat = Json.format[Tag]
    implicit val tagHandler = Macros.handler[Tag]
  }

  case class Rating(count: Int, value: Int)
  object Rating {
    implicit val ratingFormat = Json.format[Rating]
    implicit val ratingHandler = Macros.handler[Rating]
  }

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

  implicit val artistReads = (
    (__ \ "id").read[Int] ~
    (__ \ "gid").read[String] ~
    (__ \ "name").read[String] ~
    (__ \ "sort_name").read[String] ~
    (__ \ "area").readNullable[String] ~
    (__ \ "aliases").readNullable[Seq[Alias]] ~
    (__ \ "begin").readNullable[Date] ~
    (__ \ "end").readNullable[Date] ~
    (__ \ "tags").readNullable[Seq[Tag]] ~
    (__ \ "rating").readNullable[Seq[Rating]] // TODO: "rating" can be a singleton!
  )(Artist)

  implicit val artistWrites = Json.writes[Artist]
  implicit val artistHandler = Macros.handler[Artist]

  def artists(): Iterator[Artist] =
    Source.fromURL(getClass.getResource("/artist.json"))
      .getLines
      .map(Json.parse(_).asOpt[Artist])
      .flatMap(a => a)

}
