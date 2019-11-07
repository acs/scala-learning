package acs.samples

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration._
import scala.util.Random

// https://danielwestheide.com/blog/the-neophytes-guide-to-scala-part-9-promises-and-futures-in-practice/

object FutureAndPromises extends App {
  // Trying to understand the difference between Futures (in) and Promises (out)
  // Until now, Future is something to be done and Promise is something that you can ask
  // to be done now!
}