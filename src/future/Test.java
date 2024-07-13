package future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Test {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        testCompletableInfo();
    }

    public static void testCompletableInfo() throws InterruptedException, ExecutionException {
        long startTime = System.currentTimeMillis();

        // 调用用户服务获取用户基本信息
        CompletableFuture<String> userFuture = CompletableFuture.supplyAsync(() ->
        // 模拟查询商品耗时500毫秒
        {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "用户A";
        });

        // 调用商品服务获取商品基本信息
        CompletableFuture<String> goodsFuture = CompletableFuture.supplyAsync(() ->
        // 模拟查询商品耗时500毫秒
        {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "商品A";
        });

        System.out.println("获取用户信息:" + userFuture.get());
        System.out.println("获取商品信息:" + goodsFuture.get());

        // 模拟主程序耗时时间
        Thread.sleep(600);
        System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");
    }
}
