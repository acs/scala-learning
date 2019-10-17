package acs

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
  * (https://medium.com/free-code-camp/demystifying-the-monad-in-scala-cc716bb6f534)
  *
  * I do a lot of streaming data processing, with parallelism. Monoid, Applicative, and Monad are
  * very important concepts in my work
  * (https://www.reddit.com/r/scala/comments/75wli5/functional_scala_libraries_that_you_like/)
  */

object Monads {

  //  Short Monad Example in Scala: https://gist.github.com/johnynek/4191761 (scalaz)

  // Option
  // Either
  // flatMap


  trait M[A] {
    def flatMap[B](f: A => M[B]): M[B]
  }

  def unit[A](x: A): M[A]


}
