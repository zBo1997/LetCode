package Day1;

/**
 * @Classname InsertionSort
 * @Description 选择排序
 * @Date 2021/8/12 17:09
 * @Created by ZhuBo
 */
public class InsertionSort {

    public static void insertionSort9(int[] arr) {
        if (arr == null || arr.length < 0) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            /* 从前往后遍历，最小的动作是判断后一个是否比前一个数小， */
            for (int j = i - 1; j >=  0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j , j + 1);
            }
        }
    }

    private static void swap(int[] arr, int j, int i) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
