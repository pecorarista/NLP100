package nlp100.utils

object Ngram {

  def ngram[T](n: Int, s: List[T]) : List[List[T]] =
    if(s.length - n >= 0)
      s.take(n) :: ngram(n, s.tail)
    else
      Nil

  def bigram[T](s: List[T]): List[List[T]] = ngram(2, s)

}
