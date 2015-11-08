package nlp100.chapter02

import scala.io.Source
import scala.io.StdIn

object Q15 extends App {

  val POSITIVE_INT = "正の整数で入力してください"
  val TOO_LONG = "入力された数%dが全体の行数%dより大きいので全体を表示しています。"

  while(true) {
    println("末尾の何行を表示しますか。（Ctrl-C で終了します。）")
    val in: Either[Int, String] =
      try {
        val n = StdIn.readLine.toInt
        if(n > 0)
          Left(n)
        else
          Right(POSITIVE_INT)
      }
      catch {
        case _: Exception => Right(POSITIVE_INT)
      }

    in match {
      case Left(n) => {
        val ls = Source.fromURL(getClass.getResource("/hightemp.txt")).getLines.toList
        if(n > ls.size) println(TOO_LONG.format(n, ls.size))
        ls.drop(ls.size - n).foreach(println(_))
      }
      case Right(s) => println(s)
    }
    print(System.lineSeparator)
  }

}
