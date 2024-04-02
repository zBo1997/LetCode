package transmittableThreadLocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhubo
 * @date 2020/5/28 15:14
 **/
public class TransmittableThreadLocalTest {
    private static ThreadLocal<String> ttl = new TransmittableThreadLocal<>();

    /** 保证只有1个线程,以便观察这个线程被多个Runnable复用时，能否成功完成ThreadLocal的传递 **/
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            2,2,0,TimeUnit.SECONDS,new ArrayBlockingQueue<>(10)
    );

    public static void main(String[] args) throws InterruptedException {
        ttl.set("yogurtzzz");

        for (int i = 0; i < 5; i++) {
            if (i == 2) {
                ttl.set("changed");
            }
            TtlRunnable runnable = TtlRunnable.get(() -> {
                System.out.println(Thread.currentThread().getName() + " : " + ttl.get());
            });

            threadPoolExecutor.execute(runnable);
            //CompletableFuture.runAsync(runnable,threadPoolExecutor);
            TimeUnit.MILLISECONDS.sleep(500);
        }
    }
}