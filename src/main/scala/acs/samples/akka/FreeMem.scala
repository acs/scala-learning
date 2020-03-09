package acs.samples.akka

import scala.util.Random
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

import akka.actor.PoisonPill
import akka.actor.{Actor, ActorSystem, Props}


case object Produce

object FreeMem extends App {
  val actorSystem = ActorSystem()
  val actor = actorSystem.actorOf(Props[Parent])

  Console.println("Press enter ...")
  io.StdIn.readLine()
  Console.println("Starting the memory test")

  actorSystem.scheduler.schedule(2 seconds, 200 millis, actor, Produce)
//  actorSystem.scheduler.schedule(2 seconds, 200 millis, actor, Produce)
//  actorSystem.scheduler.schedule(2 seconds, 200 millis, actor, Produce)
//  actorSystem.scheduler.schedule(2 seconds, 200 millis, actor, Produce)
//  actorSystem.scheduler.schedule(2 seconds, 200 millis, actor, Produce)
//  actorSystem.scheduler.schedule(2 seconds, 200 millis, actor, Produce)
//  actorSystem.scheduler.schedule(2 seconds, 200 millis, actor, Produce)
//  actorSystem.scheduler.schedule(2 seconds, 200 millis, actor, Produce)
//  actorSystem.scheduler.schedule(2 seconds, 200 millis, actor, Produce)
//  actorSystem.scheduler.schedule(2 seconds, 200 millis, actor, Produce)
//  actorSystem.scheduler.schedule(2 seconds, 200 millis, actor, Produce)
//  actorSystem.scheduler.schedule(2 seconds, 200 millis, actor, Produce)
}

class Parent extends Actor {
  override def receive: Receive = {
    case Produce =>
      val child = context.system.actorOf(Props[Child])
      child ! Produce
  }
}

class Child extends Actor {
  val data = new Array[Byte](1000000)

  override def receive: Receive = {
    case Produce =>
      Random.nextBytes(data)
      context.system.scheduler.scheduleOnce(1 second, self, PoisonPill)
  }
}
