package SystudyTest;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @Classname Test5
 * @Description 验证码推荐生成算法
 * @Date 2021/8/11 17:38
 * @Created by ZhuBo
 */
public class Test5 {

    public String createSmsCode() {
        return String.valueOf(100000 + ThreadLocalRandom.current().nextInt(899999));
    }
    public static void main(String[] args) {
        Test5 test5 = new Test5();
       for (int i = 0; i < 1000000; i++) {
           /*  String smsCode = test5.createSmsCode();
            System.err.println(smsCode += "==>" + smsCode.length());*/
           System.err.println(String.valueOf(100000 + ThreadLocalRandom.current().nextInt(899999)));
       }
        String code = String.valueOf((int) ((Math.random() * 9 + 1) * Math.pow(10, 5)));
        System.out.println(code);
        System.out.println((Math.random() * 9 + 1) * Math.pow(10, 5));
        System.out.println(ThreadLocalRandom.current().nextInt(899999));
    }
}
