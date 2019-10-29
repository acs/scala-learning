package acs.samples.monads

/* The goal of this sample is to show why monads are so useful

https://stackoverflow.com/questions/2704652/monad-in-plain-english-for-the-oop-programmer-with-no-fp-background/28135478#28135478

Composing functions is the reason why we want to use monads and should care about monads.
Working with map and flatMap might feel familiar to anyone who has worked with the pipe operator in Unix-based languages
https://blog.redelastic.com/a-guide-to-scala-collections-exploring-monads-in-scala-collections-ef810ef3aec3
*/


object WhyMonads extends App {

  // Interacting with Monads transparently

  def divide(n:Int, d:Int): Int = {
    if (n!=0) n/d else 0
  }

  val add1 = (i: Int) => i + 1

  val double = (i: Int) => i * 2

  val addThenDouble = add1 andThen double

  /* val divideChainh = divide andThen divide andThen divide

  assert(divide(divide(10, 5).getOrElse(0), 2).get == 1) */

  // Ugly code that can not be chained! Let's build a Monad  with the divide computation
  /* class Divide[T] (n:T, d:T) {

    private def divide[T](n:T, d:T): Option[T] = {
      if (n!=0) Some(n/d) else None
    }

    def map[B](f:(T, T) => B): Divide[B] = {
      Divide(f(n), f(d))
    }

    def flatMap[B](f:(T, T) => Divide[B]): Divide[B] = {
      val Divide(b, s1) = f(a)
      Debug(b, s + s1)

    }
  }

  object Divide {
    // Unit method for the Monad
    def apply[T](n:T, d:T) = new Divide[T] (n,d)
  } */

}
