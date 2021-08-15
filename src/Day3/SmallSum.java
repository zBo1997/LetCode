package Day3;

/**
 * @Classname SmallSum
 * @Description 小和问题（深入理解归并排序）
 * @Date 2021/8/15 23:49
 * @Created by ZhuBo
 */
public class SmallSum {

    public static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid)
                + process(arr, mid + 1, r)
                + merge(arr, l, mid, r);//左右两边整体都排好序，那么就再去找小和
    }

    private static int merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int i = 0;
        int pl = l;
        int pr = mid + 1;
        int result = 0;
        while (l < mid && mid + 1 < r) {
            //求和：左边这个数和右边这个数如果小的话就算小和，并且计算为 乘以小于这个数的数量，因为这一组的左边已经排好序了比较时候已经
            result += arr[pl] <= arr[pr] ? (r - pr + 1) * arr[pl] : 0;
            help[i++] = arr[pl] < arr[pr] ? arr[pl++] : arr[pr++];
        }
        while(pl <= mid){
            arr[i++] = arr[pl++];//递增，不然help数组越界
        }
        while(pr <= r){
            arr[i++] = arr[pr++];//递增，不然help数组越界
        }
        //刷入arr数组
        for (int j = 0; j < arr.length; j++) {
            arr[l++] = help[j];
        }
        return result;
    }

}
