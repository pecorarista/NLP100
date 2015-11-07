package nlp100.chapter03

import nlp100.utils.Countries.ukInfo
import scala.xml.XML
import scala.io.Source
import java.net.URLEncoder

object Q29 extends App {

  Q28.ukInfoTrimmed.get("国旗画像") match {
    case Some(s) => {
      val filename= s.toString.replace(" ", "_")
      val page = Source.fromURL(s"""https://ja.wikipedia.org/wiki/ファイル:${URLEncoder.encode(filename, "UTF-8")}""")
      val html = XML.loadString(page.getLines.mkString)
      val src =
        (html \\ "div").map(
          d =>
            if((d \ "@class").text == "fullMedia") {
              "https:" + (d \ "a" \ "@href").text
            }
            else {
              ""
            }
        ).mkString
      println(src)
    }
    case _ => ()
  }

}
