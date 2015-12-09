package models

import play.api.libs.json.Json
import play.modules.reactivemongo.json.BSONFormats._
import reactivemongo.bson.BSONObjectID

case class Page[A](items: Seq[A], page: Int, offset: Long, total: Long) {
  lazy val prev = Option(page - 1).filter(_ >= 0)
  lazy val next = Option(page + 1).filter(_ => (offset + items.size) < total)
}

case class Alias(name: String, sort_name: String)
object Alias { implicit val aliasFormat = Json.format[Alias] }

case class Date(year: Option[Int], month: Option[Int], date: Option[Int])
object Date { implicit val dateFormat = Json.format[Date] }

case class Tag(count: Int, value: String)
object Tag { implicit val tagFormat = Json.format[Tag] }

case class Rating(count: Int, value: Int)
object Rating { implicit val ratingFormat = Json.format[Rating] }

case class Artist(_id: BSONObjectID, id: Int, gid: String, name: String, sort_name: String, area: Option[String], aliases: Option[Seq[Alias]], begin: Option[Date], end: Option[Date], tags: Option[Seq[Tag]], rating: Option[Rating])
object Artist { implicit val artistFormat = Json.format[Artist] }
