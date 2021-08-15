package Day3;

/**
 * @Classname MergeSort
 * @Description 归并排序非递归 https://www.cnblogs.com/fivestudy/p/10075421.html 图例
 * @Date 2021/8/15 16:48
 * @Created by ZhuBo
 */
public class MergeSortNotRecursive {


    private static void process(int[] arr) {
        int N = arr.length;
        int mergeSize = 1;//划分单位
        while (mergeSize < N) {
            int L = 0;//最左起始位置
            while (L < N) {
                int M = L + mergeSize - 1;
                if (M >= N) {//中间位置已经超过长度
                    break;//肯定有序
                }
                int R = Math.min(M + mergeSize, N - 1);//右边 （可能出现最右边一组凑不齐的情况）
                merge(arr, L, M, R);
                L = R + 1;//下一个左组的位置
            }
            if (mergeSize > N / 2) {
                break;
            }
            mergeSize <<= 1;
        }
    }

    /**
     * 归并
     * O（n*logn）
     * @param arr
     * @param l
     * @param m
     * @param r
     */
    private static void merge(int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1];//设定固定的存储数组
        int i = 0;
        int pl = l;
        int pr = m + 1;
        while (pl <= m && pr <= r) {
            help[i++] = arr[pl] <= arr[pr] ? arr[pl++] : arr[pr++];
        }
        while (pl <= m) {
            help[i++] = arr[pl++];//递增，不然help数组越界
        }
        while (pr <= r) {
            help[i++] = arr[pr++];//递增，不然help数组越界
        }
        for (int j = 0; j < help.length; j++) {
            arr[l++] = help[j];
        }
    }

    public static void main(String[] args) {
        int[] arr = {9, 8, 10, 6, 11, 4, 3, 2, 1};
        process(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }

    }
}
