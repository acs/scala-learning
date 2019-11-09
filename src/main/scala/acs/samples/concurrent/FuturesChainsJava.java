package acs.samples.concurrent;

// Sample from which I started
// https://medium.com/@daniel.blazevski/hello-world-example-of-futures-in-java-8-1d3b73caad02

// Nice tutorial for CompletionStage: https://www.deadcoderising.com/java8-writing-asynchronous-code-with-completablefuture/
// And from Play Java doc: https://www.playframework.com/documentation/2.7.x/JavaAsync

// The goal of this example is to show howto chain futures in Java

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;


public class FuturesChainsJava {

  // Used to minimize code later, can't do Thread.sleep(t) w/o try/catch statement.
  static void delay(long t) {
    try { Thread.sleep(t); }
    catch (InterruptedException e) {System.out.println(e);}
  }

  static CompletionStage<String> trainModel(String model) {
    return CompletableFuture.supplyAsync(() -> {
        delay(1000L);
        return "Model trained!";
      }
    );
  }

  // method that creates a Future to infer from a model
  static CompletionStage<String> inferFailure(String model, String params) {
    return CompletableFuture.supplyAsync(() -> {
        delay(1000L);
        return "Failure will appear soon!";
      }
    );
  }

  // To predict a failure, train a model and use it to infer failures
  static CompletableFuture<String> predictMachine(String model, String params) {
    CompletionStage<String> trainedModel = trainModel(model);
    return inferFailure(model, params)
      .thenCombineAsync(trainedModel, (failure, model1) -> failure)
      .toCompletableFuture();
  }


  public static void main(String[] args) {
    CompletableFuture<String> f = predictMachine("modelData", "paramsData");
    System.out.println(f.join());
  }
}