package SystudyTest.time_task;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimerTaskTest {

  private final static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

  public static void main(String[] args) throws InterruptedException {
    taskA();
  }


  public static void taskA() throws InterruptedException {
    System.out.println("我是任务A开始执行");
    scheduler.scheduleAtFixedRate(() -> {
      try {
        System.out.println("任务A子任务开始执行");
        Thread.sleep(1000);
        System.out.println("任务A子任务开始执结束");
      } catch (Exception e) {
        System.err.println("任务A子任务开始执错误");
      }
    }, 0, 3, TimeUnit.SECONDS);
    Thread.sleep(10000);
    scheduler.shutdown();
    System.out.println("我是任务A执行完成");
  }
}
