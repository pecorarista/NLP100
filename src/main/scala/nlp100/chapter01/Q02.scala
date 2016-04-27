package nlp100.chapter01

object Q02 extends App {
  println(
    "パトカー"
      .zip("タクシー")
      .flatMap({ case (x, y) => x.toString + y.toString })
      .mkString("")
  )
}
