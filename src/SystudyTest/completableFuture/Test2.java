package SystudyTest.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class Test2 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        normal();
        testCompletableThenAccept();
    }

    public static void testCompletableThenAccept() throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        CompletableFuture<String> cp1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "dev";

        });
        CompletableFuture<Void> cp2 = cp1.thenAccept((a) -> {
            System.out.println("上一个任务的返回结果为: " + a);
        });
        cp2.get();
        Thread.sleep(600);
        System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");
    }

    public static void normal() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Supplier<String> cp1 = () -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            return "dev";

        };

        String string = cp1.get();
        Thread.sleep(600);
        System.out.println(" " + string);
        System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");
    }
}
