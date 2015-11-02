package nlp100.chapter03

import nlp100.utils.Countries.uk

object Q21 extends App {

  uk.split("\n").filter(_.startsWith("[[Category")).foreach(println(_))

}
