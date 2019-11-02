package acs.samples.monads

/*
To understand the Monad concept a first step is to understand Functors, which
has the unit (wrap) and map operation but not the flatMap. The understand Applicatives, which
wraps functions. And then, how monads are more powerful than functors and applicatives?
Let's put the focus in this map vs flatMap comparison to get more insights about Monads.


Some references:

«so this is what is a Monad. It’s like a Functor who knows how to flatten.»
https://medium.com/beingprofessional/understanding-functor-and-monad-with-a-bag-of-peanuts-8fa702b3f69e

Haskell has wonderful articles about Monads (maybe I should learn fist in Haskell?):
http://adit.io/posts/2013-04-17-functors,_applicatives,_and_monads_in_pictures.html (Haskell, intuitive approach)
http://learnyouahaskell.com/a-fistful-of-monads (http://learnyouahaskell.com/chapters)
https://wiki.haskell.org/Typeclassopedia
https://wiki.haskell.org/Research_papers/Functional_pearls

Monads et all with scalaz
http://etorreborre.blogspot.com/2011/06/essence-of-iterator-pattern.html

 */

object Functors extends App {

  // Bag is the Functor type class: unit (in case class implicit) and map functions
  // map must follow the identity and associative rules
  case class Bag[A](content: A) {
    // map takes the content out of the bag, applies the function and return the result in a bag
    // map must follow the identity law and the associative law
    def map[B](f: A => B): Bag[B] = Bag(f(content))
  }

  case class Sugar(weight: Double)

  // the guy who is expert at making sugar half
  def half = (sugar: Sugar) => Sugar(sugar.weight / 2)
  val sugarBag: Bag[Sugar] = Bag(Sugar(1))
  println(sugarBag.map(sugar => half(sugar)))

  // Checking the map rules
  // 1. Identity
  def identity[A](x: A): A = x
  // assert(sugarBag.map(sugar => Bag(sugar)) == sugarBag)
  assert(sugarBag.map(sugar => identity(sugar)) == sugarBag)
  // 2. Associative
  // Functor[X].map(f).map(g) == Functor[X].map(x => g(f(x))

}

object Monad extends App {

}

object Applicative extends App {

}