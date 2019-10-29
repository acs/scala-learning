package acs.samples.monads

/**
  * In this module I will play with Monads to understand them conceptually and practically
  *
  * Some definitions I have found:
  *
  * Monads are structures that represent sequential computations
  * A Monad is an object that wraps another object in Scala
  * (https://dzone.com/articles/simplifying-monads-in-scala)
  *
  * You can think of monads as wrappers. You just take an object and wrap it with a monad.
  * monads take a type parameter. We didn’t just write M; we wrote M[A]
  * monads come with two operations:
  *   unit (also known as identity or return): performs the wrapping part
  *   flatMap (also known as bind):
  *               map with A => M[B]                  flatten
  *     M[A]  ------------------------->  M[M[B]]  -----------> M[B]
  *  FlatMap is way more powerful than map. It gives us the ability to chain operations together
  *  Monad is not a class or a trait; monad is a concept
  * (https://medium.com/free-code-camp/demystifying-the-monad-in-scala-cc716bb6f534)
  *
  * I do a lot of streaming data processing, with parallelism. Monoid, Applicative, and Monad are
  * very important concepts in my work
  * (https://www.reddit.com/r/scala/comments/75wli5/functional_scala_libraries_that_you_like/)
  *
  * Monads are Container Types
  * Monads Support Higher Order Functions
  * Monads are Combinable
  * http://james-iry.blogspot.com/2007/09/monads-are-elephants-part-1.html
  * http://james-iry.blogspot.com/2007/10/monads-are-elephants-part-2.html
  * http://james-iry.blogspot.com/2007/10/monads-are-elephants-part-3.html
  * http://james-iry.blogspot.com/2007/11/monads-are-elephants-part-4.html
  *
  * https://typelevel.org/cats/typeclasses/monad.html
  *
  * Monads as containers
  * https://blog.redelastic.com/a-guide-to-scala-collections-exploring-monads-in-scala-collections-ef810ef3aec3
  *
  *
  * A Debug Monad created the natural way
  * Just like many other functional programming tools,
  * a monad takes legitimately useful code
  * that might otherwise be very awkward to use and makes it feel more natural.
  * The monad laws just make sure we can refactor our code in the way we expect and have predictable results.
  * https://dev.to/codenoodle/don-t-read-this-monad-tutorial-2cfc
  *   https://byorgey.wordpress.com/2009/01/12/abstraction-intuition-and-the-monad-tutorial-fallacy/
  *
  *
  * With a monad, a programmer can turn a complicated sequence of functions into a succinct pipeline that
  * abstracts away auxiliary data management, control flow, or side-effects
  * https://en.wikipedia.org/wiki/Monad_%28functional_programming%29
  *
  * TODO:
  *
  * Complete the 3rd and 4th posts of http://james-iry.blogspot.com/2007/09/monads-are-elephants-part-1.html
  * Check monad laws practical checks at https://dev.to/codenoodle/don-t-read-this-monad-tutorial-2cfc
  * Check foreach and filter methods in Monad in http://james-iry.blogspot.com/2007/09/monads-are-elephants-part-2.html
  * Implement as map+flatten Debug Monad (https://dev.to/codenoodle/don-t-read-this-monad-tutorial-2cfc)
  * Study the code for Option, Either, Try, List and other simple monads
  *
  *
  */

object Monads {

  //  Short Monad Example in Scala: https://gist.github.com/johnynek/4191761 (scalaz)

  // Option
  // Either
  // flatMap


  /* A definition of what a Monad is:
  trait M[A] {
    def flatMap[B](f: A => M[B]): M[B]
  }

  def unit[A](x: A): M[A]
  */




}
