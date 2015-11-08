package nlp100.chapter01

object Q04 extends App {
  val m =
    "Hi He Lied Because Boron Could Not Oxidize Fluorine. New Nations Might Also Sign Peace Security Clause. Arthur King Can."
      .split(" ")
      .zipWithIndex
      .map({
        case (w, i) =>
          if(Seq(1, 5, 6, 7, 8, 9, 15, 16, 19).contains(i)) // scalastyle:off magic.number
            (i, w.head)
          else
            (i, w.take(2))
      }).toMap
    println(m)
}
