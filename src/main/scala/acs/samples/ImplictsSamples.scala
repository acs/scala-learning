package acs.samples

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

  // https://medium.com/@olxc/type-classes-explained-a9767f64ed2c
  // OOP polymorphism vs type classes polymorphism: https://wiki.haskell.org/OOP_vs_type_classes

  /*
   * OOP Polymorphism
   */
  trait ShapeOOP {

    def area: Double
  }

  // Implementation 1
  class CircleOOP(radius: Double) extends ShapeOOP {

    override def area: Double = math.Pi * math.pow(radius, 2)
  }

  // Implementation 2
  class RectangleOOP(width: Double, length: Double) extends ShapeOOP {

    override def area: Double = width * length
  }

  // Generic function
  def areaOf(shape: ShapeOOP): Double = shape.area

  // Usage
  println(areaOf(new CircleOOP(10)))
  println(areaOf(new RectangleOOP(5, 5)))

  /*
   * Type classes polymorphism
  */

  // The interface to be implemented
  trait Shape[A] {
    def area(a: A): Double
  }

  // The data and the implementation are separated
  // The data is defined as case classes
  case class Circle(radius: Double)
  case class Rectangle(width: Double, length: Double)

  // Implicit objects are used to convert from Shape[T] to its implementation
  implicit object CircleShape extends Shape[Circle] {
    override def area(circle: Circle) : Double = math.Pi * math.pow(circle.radius, 2)
  }

  // And here >
  implicit object RectangleShape extends Shape[Rectangle] {
    override def area(rectangle: Rectangle): Double = rectangle.width * rectangle.length
  }

  // The implicit method is the implementation of the classes
  def areaOf[A](shape: A)(implicit shapeImpl: Shape[A]): Double = shapeImpl.area(shape)

  println(areaOf(Circle(10)))
  println(areaOf(Rectangle(5,5)))

}
