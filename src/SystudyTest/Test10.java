package SystudyTest;


/**
 * @Classname Test8
 * @Description 子线程共享父线程的参数
 * @Date 2021/7/26 11:32
 * @Created by ZhuBo
 */
public class Test10 {

    public static void main(String[] args) {

        new Thread(() -> {
            pong();
        });

    }

    static void pong() {
        System.out.println("pong");
    }
}
