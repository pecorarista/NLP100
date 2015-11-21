package nlp100.chapter04

import nlp100.utils.Neko
import nlp100.utils.Neko.Morph
import nlp100.utils.Neko.MeCab

object Q30 {

  def morphs(): Seq[Seq[Morph]] = {
    Neko.morphs(MeCab)
  }

}
