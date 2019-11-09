package acs.samples.concurrent

import scala.concurrent.Future


/*
  The goal of this samples is to compare Scala and Java futures
 */
object FuturesJavaVsScala extends App {

  // Scala "Hello World" version
  val executor =  scala.concurrent.ExecutionContext.global
  val futureScala: Future[String] = Future {
    Thread.sleep(1000L)
    "Hello World!"
  } (executor)

  // Java "Hello World" version


  Thread.sleep(2000L)
}
