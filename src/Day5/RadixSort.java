package Day5;

/**
 * @Classname RadixSort
 * @Description 基数排序（也是基于桶排序）
 * @Date 2021/8/23 22:23
 * @Created by ZhuBo
 */
public class RadixSort {

    public static void radixSort(int arr[]) {
        if (arr.length < 2 || arr.length < 0) {
            return;
        }
        radixSort(arr, 0, arr.length, maxBits(arr));
    }

    /**
     * 获取数组中的最大值，的位数
     *
     * @param arr
     * @return
     */
    private static int maxBits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int bits = 0;//数组最大的位数
        while (max != 0) {
            bits++;//位数加1
            max /= 10;
        }
        return bits;
    }

    /**
     * 基数排序
     *
     * @param arr
     * @param L
     * @param R
     * @param maxBits
     */
    public static void radixSort(int[] arr, int L, int R, int maxBits) {
        final int radix = 10;
        int i = 0, j = 0;
        //准备的桶的辅助空间
        int help[] = new int[R - L + 1];
        //按照位数遍历
        for (int k = 0; k < maxBits; k++) {
            //创建每个位数的模拟队列桶
            int [] count = new int[radix];
            for ( i = L;i  < R; i++) {
                j = getDigit(arr[i],k);//当前值所在桶中位置
                count[j]++;//次位置自增，没出现一回加1
            }
            for (i = 1;i < radix; i++) {
                count[i] = count[i] + count[i - 1];//通过错位相加，计算得出，每一位数字
            }
            for (i = R; i >= L; i--) {
                //从数组的右到左进行遍历，（为什么从右到左？？）
                // 因为我们为了模仿先进显先出的队列 ，减少空间复杂度，计算了当前位数，在桶中的个数
                j = getDigit(arr[i],k);
                help[count[j] -1] = arr[i];
            }
            for ( i = L; i <= R ; i++) {
                arr[i] = help[j];
            }
        }
    }

    /**
     * 根据d选择对应位置上的数
     * @param x
     * @param d
     * @return
     */
    public static int getDigit(int x, int d) {
        return ((x / (int) Math.pow(10, d - 1)) % 10);
    }
}
