package nlp100.chapter06

import java.io.{PrintWriter, StringReader}
import org.apache.lucene.analysis.en.PorterStemFilter
import org.apache.lucene.analysis.core.WhitespaceTokenizer
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute
import scala.io.Source

object Q52 extends App {
  val tokenizer = new WhitespaceTokenizer()
  val stream = new PorterStemFilter(tokenizer)
  val charTermAttr = stream.addAttribute(classOf[CharTermAttribute])

  val p = new PrintWriter("src/main/resources/Q52.txt")
  Source.fromURL(getClass.getResource("/Q51.txt")).getLines foreach {
    word => {
      stream.close
      tokenizer.setReader(new StringReader(word))
      stream.reset
      while(stream.incrementToken) {
        p.println(s"$word\t${charTermAttr.toString}")
      }
    }
  }
  p.close

}
