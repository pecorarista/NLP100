package nlp100.chapter06

import java.io.PrintWriter
import scala.io.Source
import scala.collection.mutable.Queue

object Q50 extends App {

  val p = new PrintWriter("src/main/resources/Q50.txt")

  Source.fromURL(getClass.getResource("/nlp.txt"))
    .getLines
    .filterNot(l => l == "" || l.matches("^[A-Z][^.]+$"))
    .flatMap(splitIntoSentences(_))
    .foreach(p.println(_))

  p.close

  def splitIntoSentences(l: String): Queue[String] = {

    object State extends Enumeration {
      val Default = Value
      val Punct = Value
      val Whitespace = Value
      val EOS = Value
    }
    import State._

    var state = Default
    var s = Queue.empty[Char]
    var ss = Queue.empty[String]
    for(c <- l) {
      state =
        if(c == '.' || c == ';' || c == ':' || c == '?' || c == '!')
          Punct
        else if((state == Punct || state == Whitespace) && c.isWhitespace)
          Whitespace
        else if(state == Whitespace && c.isUpper)
          EOS
        else
          Default

      if(state == EOS) {
        ss.enqueue(s.mkString.trim)
        s = Queue.empty[Char]
      }
      s.enqueue(c)

    }
    if(s.nonEmpty) ss.enqueue(s.mkString)

    ss
  }

}
