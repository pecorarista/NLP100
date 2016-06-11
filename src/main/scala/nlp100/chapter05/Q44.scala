package nlp100.chapter05

import scalax.collection.Graph
import scalax.collection.GraphEdge._
import scalax.collection.io.dot._
import implicits._

object Q44 extends App {

  val root = DotRootGraph(
    directed = true,
    id = Some("MyDot"),
    attrStmts = List(DotAttrStmt(Elem.node, List(DotAttr("shape", "record")))),
    attrList = List(
      DotAttr("attr_1", """"one""""),
      DotAttr("attr_2", "<two>")
    )
  )
  Graph.empty[Int, DiEdge].toDot(root, _ => None)

}
