package SystudyTest;

/**
 * @Classname Test
 * @Description 子线程共享父线程的参数
 * @Date 2021/7/26 11:32
 * @Created by ZhuBo
 */
public class Test {

/*    public static void main(String[] args) throws InterruptedException {
        ThreadLocal<String> threadLocal = new ThreadLocal<String>();
        System.out.println("current thread Name ==> "+ Thread.currentThread().getName());
        threadLocal.set("test");

        Thread thread = new Thread(() -> {
            System.out.println("inner thread is --> "+Thread.currentThread().getName() + "==>" +threadLocal.get());
        });
        thread.start();
        Thread.sleep(1000);
    }*/

    public static void main(String[] args) throws InterruptedException {
        ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
        System.out.println("current thread Name ==> "+ Thread.currentThread().getName());
        threadLocal.set("test");

        Thread thread = new Thread(() -> {
            System.out.println("inner thread is --> "+Thread.currentThread().getName() + "==>" +threadLocal.get());
        });
        thread.start();
        Thread.sleep(1000);
    }

}
