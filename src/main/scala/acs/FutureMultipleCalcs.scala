package acs

import scala.concurrent.Await
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.{Failure, Success}
import scala.util.Random

// Sample from: https://alvinalexander.com/scala/concurrency-with-scala-futures-tutorials-examples

object Cloud {

  def runAlgorithm(i: Int): Future[Int] = Future {
    Thread.sleep(Random.nextInt(500))
    val result = i + 10
    println(s"returning result from cloud: $result")
    result
  }
}

object FutureMultipleCalcs extends App {

  println("starting futures")
  val result1 = Cloud.runAlgorithm(10)
  val result2 = Cloud.runAlgorithm(20)
  val result3 = Cloud.runAlgorithm(30)

  println("before for-comprehension")
  val result = for {
    r1 <- result1
    r2 <- result2
    r3 <- result3
  } yield (r1 + r2 + r3)

  println("before onSuccess")
  result onSuccess {
    // When the result is completed (i.e. all futures are completed) with Success then
    case result => println(s"total = $result")
  }

  println("before sleep at the end")
  Thread.sleep(2000) // important: keep the jvm alive
}

object FutureLife extends App {

  // A future execution depends on other future result

  class Bar(name: String) {

    def isOpen(): Future[Boolean] = Future {
      Thread.sleep(Random.nextInt(500))
      Random.nextBoolean()
    }

    def hasBeer(): Future[Boolean] = Future {
      Thread.sleep(Random.nextInt(500))
      Random.nextBoolean()
    }
  }

  def drinkBeerInFuture(bar: Bar): Future[Future[Future[Boolean]]] = Future {
    bar.isOpen().map {
      case true => bar.hasBeer()
      case _ => throw new Exception("Bar is closed")
    }
  }

  def drinkBeerNow(bar: Bar) = {
    val beer = Await.result(
      bar.isOpen().flatMap {
        case true => bar.hasBeer()
        case _ => Future(false)
      }, 10 seconds)
    if (beer) println("Drinking beer")
    else println("Can not drink beer! :(")
  }

  /* For tips on reducing nested Futures for serializig them:
    https://www.michaelpollmeier.com/execute-scala-futures-in-serial-one-after-the-other-non-blocking
    In our case we don't need to serialize them but I will do it
   */
  def drinkBeerNowSlate(bar: Bar) = {
    val checks = Future.sequence(List(bar.isOpen(), bar.hasBeer()))
    val res = Await.result(checks, 10 seconds)
    if (res(0) && res(1)) println("Drinking beer")
    else println("Can not drink beer! :(")
  }

  drinkBeerNow(new Bar("DancingKing"))
  drinkBeerNowSlate(new Bar("DancingKing"))
}