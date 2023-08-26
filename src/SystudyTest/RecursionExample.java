package SystudyTest;

import java.util.function.IntFunction;

public class RecursionExample {
    public static void main(String[] args) {
        IntFunction<Integer> factorial = RecursionExample::calculateFactorial;

        int result = factorial.apply(5);
        System.out.println(result); // 输出 120
    }


    private static int calculateFactorial(int n) {
        if (n <= 1) {
            return 1;
        } else {
            return n * calculateFactorial(n - 1);
        }
    }
}
