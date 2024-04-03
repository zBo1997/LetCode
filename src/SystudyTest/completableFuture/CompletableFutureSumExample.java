package SystudyTest.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureSumExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture.supplyAsync(() -> {
            return 10;
        }).thenApplyAsync(result -> {
            System.out.println("我加20");
            return result + 20;
        }).thenApplyAsync(result -> {
            System.out.println("我加30");
            return result + 30;
        }).thenAccept(result -> {
            System.out.println("Result: " + result);
        }).get();

    }
}