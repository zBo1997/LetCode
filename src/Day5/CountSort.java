package Day5;

import java.util.Arrays;

/**
 * @Classname CountSort
 * @Description 计数排序(底层是桶排序 ，桶排序是一种思想)
 * @Date 2021/8/23 22:08
 * @Created by ZhuBo
 */
public class CountSort {

    public static void count(int[] arr){
        if(arr == null && arr.length < 2){
            return;
        }
        //选择二者中最小的数
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max,arr[i]);
        }
        //创建一个桶
        int[] bucket = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i]] ++;//当前数组的数字所在桶的下表增加一次计数
        }
        for (int i = 0,j = 0; j < bucket.length; j++) {
            while(bucket[j] -- > 0){
                arr[i++] = j;
            }
        }

    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,5,3,2,3,4,5,32,1,1,32,12,1};
        CountSort.count(arr);
        Arrays.stream(arr).forEach(x -> System.out.println(x));
    }

}
