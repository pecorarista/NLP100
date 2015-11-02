package nlp100.chapter01

object Q03 extends App {
  val s = "Now I need a drink, alcoholic of course, after the heavy lectures involving quantum mechanics."
  val l = s.split(" ").map(w => w.filter(c => c != ',' && c != '.').length).toList
  println(l)
}
