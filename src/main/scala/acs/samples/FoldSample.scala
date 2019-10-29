package acs.samples

object FoldSample  extends App {

  val l: List[String] = List("a", "b", "c")

  l.fold () {
    println(_ , _)
  }

  /* Why this does not work? Think about it
  l.fold ("a") {
    println(_ , _)
  } */

  l.fold ("a") { (x:String, y:String) =>
    println(x, y)
    x + y
  }


}
