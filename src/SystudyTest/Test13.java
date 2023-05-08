package SystudyTest;


import java.util.ArrayList;
import java.util.List;

/**
 * @Classname Test11
 * @Description
 * @Date 2021/7/26 11:32
 * @Created by ZhuBo
 */
public class Test13 {



    public static void main(String[] args) {
        List<Integer> lists = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            lists.add(i);
        }
        for (int i = 0; i < 100; i++) {
            StringBuilder sb = new StringBuilder();
            // StringBuffer sb = new StringBuffer();
            lists.parallelStream().forEach(p -> {
                sb.append(p);
                // 可以明显看到，拼接的字符串长度越大，异常越容易发生
                sb.append("----------------------------------------");
                // stringBuilder.append("-");
            });
            System.out.println(i + ": " + sb.toString());
        }

    }

}