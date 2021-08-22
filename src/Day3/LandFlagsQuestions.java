package Day3;

/**
 * @Classname LandFlagsQuestions
 * @Description 荷兰国旗问题(三色问题) , 但是以arr[R] 作为划分的值 [从这个引申出快速排序]
 * @Date 2021/8/16 21:57
 * @Created by ZhuBo
 */
public class LandFlagsQuestions {

    public static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    /**
     * 荷兰国旗问题(三色问题) , 但是以arr[R] 作为划分的值
     *
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int[] netherLandsFlag(int[] arr, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        int less = L - 1;//小范围区域
        int more = R;//较大范围区域
        int cur = L;//游标指针
        while (cur < more) {//当游标追赶到最大区-域的的范围，循环也就结束了，标识已经已经没有最大何最小的范围
            if (arr[cur] == arr[R]) {
                cur++;
            } else if (arr[cur] < arr[R]) {
                swap(arr, cur++, ++less);//小于区域
            } else {
                swap(arr, cur, --more);//大于区域
            }
        }
        swap(arr, more, R);//再去吧最后一个数何最大区域的一个数做交换，防止R 这个数 永远不动
        return new int[]{less + 1, more};//返回区域的左边界+1 ，和最大有边界，也就相等的区域
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 1, 1, 5, 2, 1, 5, 6, 6};
        int[] ints = netherLandsFlag(arr, 0, arr.length - 1);
        for (int i : ints) {
            System.out.println(i + " ");
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
