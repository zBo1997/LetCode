package Day3;

/**
 * @Classname QuickSort
 * @Description 快速排序（核心思想是，需要有两个不同区域进行，大小值的划分，然后一一推到对应的区域即可）
 * @Date 2021/8/16 18:08
 * @Created by ZhuBo
 */
public class QuickSort {


    /**
     * 注意：在使用swap操作交换数组元素时，会遇到传入两个下标值相同的情况，这时异或操作实际上变成了
     * <p>
     * a^ =a; a^ =a; a^=a;
     * <p>
     * 所以这时a的值会变成0.
     *
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }


    /**
     * 快速排序
     */
    public static void quickSort1(int arr[], int L, int R) {
        if (L == R) {
            return;
        }
        process1(arr, L, R);
    }

    /**
     * 1.0 Partition
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int partition(int arr[], int L, int R) {
        if (L > R) {
            return -1;
        }
        if (L == R) {
            return L;
        }
        int cur = L;
        int lessEquals = L - 1;//靠右相等的那个数字
        while (cur < R) {
            if (arr[cur] <= arr[R]) {
                swap(arr, cur, ++lessEquals);
            }
            cur++;//游标指向下一位
        }
        swap(arr, ++lessEquals, R);//由于至始至终R 下标位置值是没有动过得，所以我们需要把他换到最右相等数组得得右边
        return lessEquals;
    }

    /**
     * 递归划分左组，右组
     * @param arr
     * @param L
     * @param R
     */
    public static void process1(int arr[], int L, int R) {
        if (L >= R) {
            return;
        }
        int mid = partition(arr, L, R);//获取到每一次partition后中间不需要得那个值得位置，然后进行再对他得左组，和右组做相同得动作
        process1(arr, L, mid - 1);//左组操作
        process1(arr, mid + 1, R);//又足操作
    }


    public static void main(String[] args) {
        int[] arr = {1, 5, 1, 1, 5, 2, 1, 5, 6, 6};
        quickSort1(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
