package acs.samples.monads

object MonadLaws extends App {



  class FunctorSample {
    // In Scala a functor is a class with a map method and a few simple properties
    /*
    class M[A] {
      def map[B](f: A => B):M[B] = ...
    }
    */

    /* Functor laws:
    1. Identity:
    def identity[A](x:A) = x
    m.map(x:A => identity(x):A) ≡ m
    2. How several "maps" compose together (Composition)
    m map g map f ≡ m map {x => f(g(x))}
    in for format:
    for (y <- (for (x <-m) yield g(x)) yield f(y) ≡ for (x <- m) yield f(g(x))
     */

    // all monads are functors
    // The connection between functors and monads is flatMap (bind)
    // m map f ≡ m flatMap {x => unit(f(x))}
    // m map f ≡ m map     {x =>      f(x)}
    // for (x <- m) yield f(x) ≡ for (x <- m; y <- unit(f(x))) yield y
  }

  def f(x:Int) =  x + 2
  def g(x:Int) =  x * 2

  // Functor identity
  val l = List(8, 9, 17)
  val l1 = l map (x => x)
  val l2 = for (x <- l) yield x
  assert(l.equals(l1))
  assert(l.equals(l2))

  // Functor composition: List are functors so the law must be honored
  val lc = l map (x => f(x)) map (x => g(x))
  var lc1 = l map (x => g(f(x)))
  assert(lc.equals(lc1))

  // Functor Monad connection
  // m map f ≡ m flatMap {x => unit(f(x))}
  val lmc = l map f
  // List() is unit()
  val lmc1 = l flatMap (x => List(f(x)))

  assert(lmc.equals(lmc1))
}
