package nlp100.chapter03

import nlp100.utils.Countries.ukInfo

object Q25 extends App {

  ukInfo(filter=((x: String) => x)).foreach(println(_))

}
