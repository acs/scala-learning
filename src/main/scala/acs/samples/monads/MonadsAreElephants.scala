package acs.samples.monads

/**
  * Playing with monads an with Scala syntax
  * Based on the series:
  * http://james-iry.blogspot.com/2007/09/monads-are-elephants-part-1.html
  */

object MonadsAreElephants extends App {

  /**
    * Show how monads support high order functions (map)
    * and how the monad is preserved but its container type
    * can be changed
    */
  def monMaps() {
    // Map does not change the kind of monad (List)
    def double(x: Int) = 2 * x

    val xs = List(1, 2, 3)
    val doublesLong = xs.map((x: Int) => double(x)): List[Int]
    val doubles = xs map double
    assert(doubles == doublesLong)
    // or val doubles = xs map {2 * _}
    assert(doubles == List(2, 4, 6))

    // but may change monad (Option in this sample) parameterized type
    val one: Option[Int] = Some(1)
    val oneString: Option[String] = one map (_.toString)
    val oneString1 = one map {
      _.toString
    }

    assert(oneString == Some("1"))
  }

  /**
    * Show how monads can be combined/chained
    */
  def monCombine() {

    val configApp = Map("MaxThreads" -> "10",
                        "Timeout" -> "5")

    class MyConfig {
      def fetchParam(str: String): Option[String] = {
        Option(configApp(str))
      }
    }

    object MyConfig {
      def apply(): MyConfig = new MyConfig()
    }

    def stringToInt(string: String): Option[Int] = {
      Option(string.toInt)
    }

    val opString : Option[String] = MyConfig() fetchParam "MaxThreads"
    val result = opString map stringToInt
    println(result)  // Some(Some(10))
    val resultOk = opString flatMap stringToInt
    println(resultOk)  // Some(10)
  }

  def buildMonad(): Unit = {
    class M[A](value: A) {
      // Define map from flatMap. The hard thing is to implement flatMap!
      private def unit[B] (value : B) = new M(value)
      // def map[B](f: A => B) : M[B] = flatMap {x => unit(f(x))}
      // def flatMap[B](f: A => M[B]) : M[B] = ...  // TODO: missing real implementation
    }

    class M2[A] {
      // Define flatMap from man and flatten
      private def unit[B] (value : B) = new M(value)
      // private def flatten[B](x:M[M[B]]) : M[B] = ...
      // def map[B](f: A => B) : M[B] = ...
      // def flatMap[B](f: A => M[B]) : M[B] = flatten(map(f))
    }
  }

  def monadForComprehension(): Unit = {
    // Playing with monads and for comprehension
    val ns = List(1, 2)
    // It looks like a loop but it is not (it is a map)
    val qs = for {n <- ns} yield n * 2
    assert (qs == List(2, 4))
    // Doing the same with a map
    assert (qs == ns.map(_*2))

    val ns1 = List(1, 2)
    val os1 = List (4, 5)
    // for [each] n [in] ns1 [and each] o [in] os1 yield n * o
    val qs1 = for (n <- ns1; o <- os1)
      yield n * o
    assert (qs1 == List (1*4, 1*5, 2*4, 2*5))
    // Doing the same with flatMap
    val qs2 = ns1 flatMap {n => os1 map {o => n * o }}
    assert (qs2 == qs1)
  }

  // monMaps()
  // monCombine()
  monadForComprehension()
}
