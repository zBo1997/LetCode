package Day15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class RemoveDupArray {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int resultOnce = removeOnce(nums);
        int resultTwice = removeTwice(nums);
        System.out.println(resultOnce);
        System.out.println(resultTwice);
    }

    /**
     * 原地移除数组中重复数字 出现一次
     *
     * @param array
     * @return
     */
    public static int removeOnce(int[] array) {
        int p = 0;
        int q = 1;

        while (q < array.length) {
            if (array[p] != array[q]) {
                array[p + 1] = array[q];
                p++;
            }
            q++;
        }
        return p + 1;
    }

    /**
     * 变种原地移除数组中重复数字 出现两次
     *
     * @param array
     * @return
     */
    public static int removeTwice(int[] array) {
        for (int i = 0; i < array.length; i++) {

        }
        return 0;
    }
}
