package Day1;

/**
 * @Classname InsertionSort
 * @Description 数组中只有一种数字出现了级数次
 * @Date 2021/8/12 17:09
 * @Created by ZhuBo
 */
public class EvenOddTimesNum {

    public static void insertionSort9(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println(eor);
    }


}
