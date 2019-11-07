package acs.samples

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.Promise
import scala.concurrent.duration._
import scala.util.Random
import scala.util.{Success, Failure}

// https://danielwestheide.com/blog/the-neophytes-guide-to-scala-part-9-promises-and-futures-in-practice/

/* The Future type only provides an interface for reading the value to be computed.
   The task of writing the computed value is achieved via a Promise. Hence, there is a clear separation
   of concerns in the API design.
 */

/* Making blocking code concurrent can be pretty easy by wrapping it in a call to future. However,
   it's better to be non-blocking in the first place. To achieve this, one has to make a Promise to complete a Future
 */


object FutureAndPromises extends App {
  // Trying to understand the difference between Futures and Promises
  // Until now, Future is something to will be done and Promise is something that you can ask
  // to be done now!

  case class TaxCut(reduction: Int)

  object Government {
    def redeemCampaignPledge(): Future[TaxCut] = {
      val p = Promise[TaxCut]()
      Future {
        println("Starting the new legislative period.")
        Thread.sleep(2000)
        p.success(TaxCut(20))
        println("We reduced the taxes! You must reelect us!!!!1111")
      }
      p.future
    }
  }

  val taxCutF: Future[TaxCut] = Government.redeemCampaignPledge()
  println("Now that they're elected, let's see if they remember their promises...")
  taxCutF.onComplete {
    case Success(TaxCut(reduction)) =>
      println(s"A miracle! They really cut our taxes by $reduction percentage points!")
    case Failure(ex) =>
      println(s"They broke their promises! Again! Because of a ${ex.getMessage}")
  }

  Thread.sleep(5000)
}