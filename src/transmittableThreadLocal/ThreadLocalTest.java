package transmittableThreadLocal;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhubo
 * @date 2020/5/28 15:14
 **/
public class ThreadLocalTest {
    private static ThreadLocal<String> ttl = new ThreadLocal<>();
    private static ThreadPoolExecutor threadPoolExecutor =
        new ThreadPoolExecutor(2, 2, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

    public static void main(String[] args) throws InterruptedException {
        ttl.set("yogurtzzz");

        for (int i = 0; i < 10000; i++) {
            if (i == 2) {
                ttl.set("changed");
            }
            Runnable runnable = () -> System.out.println(Thread.currentThread().getName() + " : " + ttl.get());

            threadPoolExecutor.execute(runnable);
            //CompletableFuture.runAsync(runnable,threadPoolExecutor);
            TimeUnit.MILLISECONDS.sleep(500);
        }
    }
}