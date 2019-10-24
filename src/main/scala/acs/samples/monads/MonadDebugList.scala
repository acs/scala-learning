package acs.samples.monads

object MonadDebugList extends App {

  // Initial inspiration code
  assert(
    List(1, 2, 3, 4, 5)
      .map(_ + 10)
      .filter(_ % 2 == 0)
      .take(2).equals(List(12, 14))
  )

  object DebugList {

    // variable argument syntax so it works like List.apply
    // e.g: A = Int,  variable argument 1,5,7 and DebugList(1, 5, 7)
    def apply[A](a: A*): DebugList[A] = DebugList(List(a: _*))
  }

  case class DebugList[A](l: List[A]) {

    def map[B](f: A => B): (DebugList[B], String) =
      // extended map syntax to be sure details are understood
      (DebugList(l.map((x:A) => f(x):B)), "mapped")

    def filter(f: A => Boolean): (DebugList[A], String) =
      (DebugList(l filter f), "filtered")

    def take(n: Int): (DebugList[A], String) =
      (DebugList(l take n), s" took$n")
  }

  // Pretty ugly to work now with DebugList
  val dl = DebugList(1, 2, 3, 4, 5)
  val (dl1, str1) = dl.map(_ + 10)
  val (dl2, str2) = dl1.filter(_ % 2 == 0)
  val (dl3, str3) = dl2.take(2)
  // To get all the debug message
  val debugStr = str1 + str2 + str3

  // Let's abstract DebugList to Debug in general
  case class Debug[A](a: A, s: String) {
    def flatMap[B](f: A => Debug[B]): Debug[B] = {
      val Debug(b, s1) = f(a)
      Debug(b, s + s1)
    }

    def map[B](f: A => B): Debug[B] =
      Debug(f(a), s)
  }

  // This was my first try for defining the apply method for Debug
  object DebugDummy {

    def apply[A](a: A, s: String): Debug[A] = new Debug(a, s)
  }

  // and this is the version provided in the tutorial
  case object Debug {
    def apply[A](x: A): Debug[A] =
      unit(x)

    def unit[A](x: A): Debug[A] =
      Debug(x, "")
  }

  object BetterDebugList {
    //variable argument syntax so it works like List.apply
    def apply[A](a: A*): BetterDebugList[A] = BetterDebugList(List(a: _*))
  }

  // return Debug type instead of tuples in its methods
  case class BetterDebugList[A](l: List[A]){
    def map[B](f: A => B): Debug[BetterDebugList[B]] =
      Debug(BetterDebugList(l map f), "mapped")

    def filter(f: A => Boolean): Debug[BetterDebugList[A]] =
      Debug(BetterDebugList(l filter f), "filtered")

    def take(n: Int): Debug[BetterDebugList[A]] =
      Debug(BetterDebugList(l take n), s"took $n")
  }

  // Back to our loved functional syntax
  val debug = Debug(BetterDebugList(1, 2, 3, 4, 5))
    .flatMap(_.map(_ + 10))
    .flatMap(_.filter(_ % 2 == 0))
    .flatMap(_.take(2))

  // to hide those explicit flatMap calls we can use for comprehensions
  val debugFor = for {
    w <- Debug(BetterDebugList(1, 2, 3, 4, 5))
    x <- w.map(_ + 10)
    y <- x.filter(_ % 2 == 0)
    z <- y.take(2)
  } yield z

  println(debugFor.s)


  print(debug)

  // Debug class is a Monad!

  // Exercises
  // Implement debug flatMap as a map and flatten
}
