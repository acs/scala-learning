package acs.samples.concurrent

import scala.concurrent.Future
import scala.concurrent.Promise
import scala.util.Failure
import scala.util.Success
import scala.concurrent.ExecutionContext.Implicits.global


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
