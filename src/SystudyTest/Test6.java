package SystudyTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Classname Test5
 * @Description 验证码推荐生成算法
 * @Date 2021/8/11 17:38
 * @Created by ZhuBo
 */
public class Test6 {

    public String createSmsCode() {
        return String.valueOf(100000 + ThreadLocalRandom.current().nextInt(899999));
    }
    public static void main(String[] args) {
        LocalDateTime parse = LocalDateTime.parse("2021-09-10 15:10:28.0", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
        System.out.println(parse);

    }
}
