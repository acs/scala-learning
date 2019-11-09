package acs.samples.concurrent;

// Sample from which I started
// https://medium.com/@daniel.blazevski/hello-world-example-of-futures-in-java-8-1d3b73caad02

// Evolution of futures in Java (Callable seems to be all stuff)
// https://blog.gilliard.lol/2017/10/23/Java-Futures-Promises.html

// The CompletableFuture is similar to the Scala Future. It is executed by the
// Java runtime at some point. It can be chainable using the CompletionStage.


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;


public class FuturesJavaVsScala {

  // Java Futures "Hello World" version

  // Used to minimize code later, can't do Thread.sleep(t) w/o try/catch statement.
  static void delay(long t) {
    try { Thread.sleep(t); }
    catch (InterruptedException e) {System.out.println(e);}
  }


  // method that creates a Promise to return "Hello World!"
  static CompletionStage<String> showGreeting() {
    return CompletableFuture.supplyAsync(() -> {
        delay(1000L);
        return "Hello World!";
      }
    );
  }

  // function that creates a Future to return "Hello World!"
  // The Future is created from the Promise (CompletionStage)
  static CompletableFuture<String> asyncHelloWorld() {
    return showGreeting().toCompletableFuture();
  }


  public static void main(String[] args) {
    CompletableFuture<String> f = asyncHelloWorld();
    System.out.println(f.join());
    System.out.println(f.join());
    System.out.println(f.join());
  }
}