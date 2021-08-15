package Day3;

/**
 * @Classname MergeSort
 * @Description 归并排序递归方法
 * 最小子任务的思想，理解递归 O（n*logn）
 * @Date 2021/8/15 16:48
 * @Created by ZhuBo
 */
public class MergeSort {

    /**
     * @param arr
     * @param L
     * @param R
     */
    public static void process(int arr[], int L, int R) {
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);//求出中间位置
        process(arr, L, mid);//左边区域
        process(arr, mid + 1, R);//右边区域
        merge(arr, L, mid, R);
    }

    /**
     * 归并排序
     *
     * @param arr
     * @param l
     * @param mid
     * @param r
     */
    private static void merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int i = 0;//新数组的的下表
        int p1 = l;//左边起始位置
        int p2 = mid + 1;//右边起始位置
        while (p1 <= mid && p2 <= r) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        //将左边剩余元素填充进help中
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        //将左边剩余元素填充进help中a
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        //把每一个归并好的数据放入到一一放入原数组
        for (int j = 0; j < help.length; j++) {
            arr[l++] = help[j];
        }

    }

    public static void main(String[] args) {
        int[] arr = {6,4,100,4,6,7,76};
        process(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.print(i + " ");
        }

    }

}
