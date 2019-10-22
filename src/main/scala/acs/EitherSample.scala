package acs

import scala.util.Failure
import scala.util.Success
import scala.util.Try


object EitherSample extends App {

  // Either improves Option adding an error cause

  val in = Console.readLine("Type Either a string or an Int: ")
  val result: Either[String, Int] = try {
    Right(in.toInt)
  } catch {
    case e: Exception =>
      Left(in)
  }

  println(result match {
    case Right(x) => s"You passed me the Int: $x, which I will increment. $x + 1 = ${x + 1}"
    case Left(x) => s"You passed me the String: $x"
  })

  Try(Console.readLine("(Try) Type Either a string or an Int: ").toInt) match {
    case Success(num) => println(s"Readed number ${num}")
    case Failure(ex) => println(s"Readed string ${ex.getMessage.split("input string: ")(1).replace("\"","")}")
  }
}
