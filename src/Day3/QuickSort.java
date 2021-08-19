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
     *
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
     *
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

    /**
     * 快速排序2.0 升级版
     * 模仿荷兰国旗问题，从而实现每次都去需需按照一段相同的区域，放到数组的中间
     *
     * @param arr
     * @param L
     * @param R
     */
    public static void process2(int arr[], int L, int R) {
        if (L >= R) {
            return;
        }
        int mid[] = areaPartition(arr, L, R);
        process2(arr, L, mid[0] - 1);
        process2(arr, mid[1] + 1, R);
    }

    /**
     * 寻找相等数字的区域，这样会增加快速排序的速度，每一次相同范围的区域，模仿荷兰国旗的问题
     *
     * @param arr
     * @param L
     * @param R
     * @return
     */
    private static int[] areaPartition(int[] arr, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        int less = L - 1;//较小范围区域
        int more = R;//较大范围区域
        int cur = L;//游标从L开始
        //终止条件是当游标如果>较大范围，那么标识当前范围已经排序完成
        while (cur < more) {
            if (arr[cur] == arr[R]) {//当前游标的值和右边的值相等，不做交换游标不懂
                cur++;
            } else if (arr[cur] <= arr[R]) {//当前游标对应的值小于划分值,那么就行交换未知，并且游标增加，较小范围增加
                swap(arr, cur++, ++less);
            } else {
                swap(arr, cur, --more);//如果当前游标对应值大于，划分值，那么游标不动，把这个数和较大数交换，较大范围增加
            }
        }
        swap(arr, more, R);//因为R的数字一直在划分，所以R的一直没有被划分过
        return new int[]{less + 1, more};//返回区域的左边界+1 ，和最大有边界，也就相等的区域
    }

    /**
     * 为了解决如果数组本身有序的最坏打算，时间复杂度导致为O(N2)，所以吧这种时间，交给该概率来解决降低时间复杂度O(N * logN)
     *
     * @param arr
     * @param L
     * @param R
     */
    public static void process3(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int[] equalArea = areaPartition(arr, L, R);
        process3(arr, L, equalArea[0] - 1);
        process3(arr, equalArea[1] + 1, R);
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 1, 1, 5, 2, 1, 5, 6, 6};
        process3(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
