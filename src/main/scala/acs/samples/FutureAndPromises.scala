package acs.samples

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration._
import scala.util.Random

// https://danielwestheide.com/blog/the-neophytes-guide-to-scala-part-9-promises-and-futures-in-practice/
//
// The Future type only provides an interface for reading the value to be computed.
// The task of writing the computed value is achieved via a Promise. Hence, there is a clear separation
// of concerns in the API design.


object FutureAndPromises extends App {
  // Trying to understand the difference between Futures (in) and Promises (out)
  // Until now, Future is something to be done and Promise is something that you can ask
  // to be done now!
}