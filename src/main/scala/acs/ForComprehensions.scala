package acs

// Init based on: https://docs.scala-lang.org/tour/for-comprehensions.html

object ForComprehensions extends App {

  case class User(name: String, age: Int)

  val userBase = List(User("Travis", 28),
    User("Kelly", 33),
    User("Jennifer", 44),
    User("Dennis", 23))

  val twentySomethings: List[String] =
    for (
      user <- userBase if (user.age >= 20 && user.age < 30)
    ) yield user.name // i.e. add this to a list

  twentySomethings.foreach(name => println(name)) // prints Travis Dennis
}

object ForComprehensionsNested extends App {

  val listOfListOfInt = List(List(1, 2, 3), List(2, 3, 4))

  val listOfInt = for {
    loi <- listOfListOfInt
    i <- loi
  } yield i + 4

  listOfInt.foreach(name => print(s"$name ")) // prints 5 6 7 6 7 8
}