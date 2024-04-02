package huaweiOd;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 排队游戏
 */
public class QueuingGame {


    //总学生数量
    private static int total_stu_count;

    //坏学生数量
    private static int bad_stu_count ;

    //最大不满值
    private static int max_angry ;

    //
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        total_stu_count = scanner.nextInt();
        bad_stu_count = scanner.nextInt();
        max_angry = scanner.nextInt();
    
        //准备坏学生的数组 准备后续遍历坏学生
        Set<Integer> badStudent = new HashSet<>();
        for (int i = 0; i < bad_stu_count; i++) {
            badStudent.add(scanner.nextInt());
        }

        //准备

    }
}
