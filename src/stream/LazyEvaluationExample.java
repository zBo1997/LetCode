package stream;

import java.util.Arrays;
import java.util.List;

public class LazyEvaluationExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Integer result = numbers.stream()
                                .filter(n -> {
                                    System.out.println("Filtering: " + n);
                                    return n % 2 == 0 && n > 5;
                                })
            .filter(n-> n != 6)
                                .findFirst()
                                .orElse(null);

        System.out.println("Result: " + result);
    }
}