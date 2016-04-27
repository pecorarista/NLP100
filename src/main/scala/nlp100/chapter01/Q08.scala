package nlp100.chapter01

object Q08 extends App {

  def cipher(c: Char): Char =
    if (c.toString.matches("""[a-z]"""))
      (219 - c.toInt).toChar
    else
      c

  println("night".map(cipher(_)))
  println("night".map(c => (cipher _ compose cipher _)(c)))

}
