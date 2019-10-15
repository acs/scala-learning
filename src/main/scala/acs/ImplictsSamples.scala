package acs

// In IntelliJ there are helps for working with implicits
// CTRL+SHIFT+P: show implicits params
// CTRL+SHIFT+P: show implicits methods

object ImplicitsParams extends App {

  // Based on https://medium.com/@olxc/https-medium-com-olxc-implicits-in-scala-b2dcfccaa9e8

  implicit val bob = "Bob"
  implicit val bob1 = Seq("Bob1")

  def greet(implicit name: String) = {
    println(s"Hello, $name!")
  }

  // Using CTRL-SHIFT-P in IntelliJ you can see the implicits used in this method (bob)
  greet

  // outputs "Hello, Bob!"

}

object ImplicitsTypeConversion extends App {
  // Convert between types using implicits methods helpers
  implicit def intToStr(num: Int): String = s"The value is $num"

  // Using CTRL-SHIFT-Q over 42 show the implicit conversion methods available
  val s = 42.toUpperCase() // evaluates to "THE VALUE IS 42"
  println(s)

  def functionTakingString(str: String) = str

  // note that we're passing int
  val s1 = functionTakingString(42) // evaluates to "The value is 42"
  println(s1)
}


object ImplicitsClass extends App {
  // Since scala 2.10
  // In this sample the type String is extended with new methods
  implicit class StringOps(str: String) {
    def yell = str.toUpperCase() + "!"
    def isQuestion = str.endsWith("?")
  }

  println("Hello world".yell) // evaluates to "HELLO WORLD!"
  println("How are you?".isQuestion) // evaluates to 'true'
}

object ImplicitsTypeClass extends App {
}
