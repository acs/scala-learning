package acs.samples.concurrent



import scala.concurrent.Future


object FuturesScalaVsJava extends App {

  // Scala "Hello World" version
  val executor =  scala.concurrent.ExecutionContext.global
  def findGreeting = {
    Thread.sleep(1000L)
    "Hello World!"
  }
  val futureScala: Future[String] = Future (findGreeting) (executor)

  futureScala.map(println(_))(executor)
  futureScala.map(println(_))(executor)
  futureScala.map(println(_))(executor)

  Thread.sleep(2000L)
}
