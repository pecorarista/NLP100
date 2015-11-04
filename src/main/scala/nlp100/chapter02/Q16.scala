package nlp100.chapter02

import scala.io.Source
import scala.io.StdIn
import scala.collection.Seq

object Q16 extends App {

  val ls = Source.fromURL(getClass.getResource("/hightemp.txt")).getLines.toList

  val POSITIVE_INT = "正の整数で入力してください"
  val TOO_LONG = s"全体の行数${ls.length}以下の数を入力してください。"

  while(true) {
    println(s"いくつに分割して表示しますか。（最大${ls.length}）")

    val in: Either[Int, String] =
      try {
        val n = StdIn.readLine.toInt
        if(n <= 0)
          Right(POSITIVE_INT)
        else if(n > ls.length)
          Right(TOO_LONG)
        else
          Left(n)
      }
      catch {
        case _: Exception => Right(POSITIVE_INT)
      }

    in match {
      case Left(n) => {
        val r = ls.length % n
        val rs = Seq.fill(r)(1) ++ Seq.fill(n - r)(0)
        val m = ls.length / n
        val ms = Seq.fill(n)(m).zip(rs).map({ case (i, j) => i + j })

        ms.zip(ms.scanLeft(0)(_ + _)).foreach({
          case (i, j) => {
            ls.drop(j).take(i).foreach(println(_))
            println("====================")
          }
        })
      }
      case Right(s) => println(s)
    }

  }
}
