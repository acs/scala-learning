package acs.samples.concurrent

/*
Scala Futures are executed async by the runtime (are resolved).
Once it is resolved (the creator that does not control when) a
promise is created to return the value. And this promise is collected
with the map/flatMap methods over the Future resolved.
 */

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits._


object FuturesScalaVsJava extends App {

  // Scala  Futures "Hello World" version

  // method that is executed by the Future (the promise to get the
  // result is created inside the map implementation)
  def showGreeting = {
    Thread.sleep(1000L)
    "Hello World!"
  }

  // Create a Future instance to execute the async code
  val futureScala: Future[String] = Future (showGreeting)

  futureScala.map(println(_))
  futureScala.map(println(_))
  futureScala.map(println(_))

  Thread.sleep(2000L)
}
