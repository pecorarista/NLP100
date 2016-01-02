package nlp100.utils

import scala.io.Source
import play.api.libs.json._
import reactivemongo.bson.Macros

object Artists {

  case class Alias(name: String, sort_name: String)
  object Alias {
    implicit val aliasFormat = Json.format[Alias]
    implicit val aliasHandler = Macros.handler[Alias]
  }

  case class Date(year: Option[Int], month: Option[Int], date: Option[Int])
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
    rating: Option[Rating]
  )
  object Artist {
    implicit val artistWrites = Json.writes[Artist]
    implicit val artistHandler = Macros.handler[Artist]
  }

  def sequence[A](json: JsValue, name: String)(implicit A: Reads[A]): Option[Seq[A]] =
    ((json \ name).asOpt[Seq[A]], (json \ name).asOpt[A]) match {
      case (Some(rs), _) => Some(rs)
      case (None, Some(s)) => Some(Seq(s))
      case (None, None) => None
    }

  def artists(): Iterator[Artist] =
    Source.fromURL(getClass.getResource("/artist.json"))
      .getLines
      .map(
        l => {
          val j = Json.parse(l)
          val id = (j \ "id").as[Int]
          val gid = (j \ "gid").as[String]
          val name = (j \ "name").as[String]
          val sort_name = (j \ "sort_name").as[String]
          val area = (j \ "area").asOpt[String]
          val aliases = sequence[Alias](j, "aliases")
          val begin = (j \ "begin").asOpt[Date]
          val end = (j \ "end").asOpt[Date]
          val tags = sequence[Tag](j, "tags")
          val rating = (j \ "rating").asOpt[Rating]
          Artist(id, gid, name, sort_name, area, aliases, begin, end, tags, rating)
        }
      )

}
