package Day2;

/**
 * @Classname GetMax
 * @Description 数组中左右两边的数组最大的值,递归方式实现
 * @Date 2021/8/15 13:29
 * @Created by ZhuBo
 * 时间复杂度公式：
 * Mater 公式
 * 满足次公式 就可以套用以下的复杂付豪段计算,前提递归已经拆分位最小子任务，才可以计算 其中a为递归次数，调用次数，b为 被递归的数量，c 为除了递归外的其他任务（的时间复杂段）
 * T(n) = a * T (N/b) + O(N^d)
 * 1、logb^a > d 的时间复杂段： O(N^log b^a)
 * 2、logb^a < d 的时间复杂度： O(N^d)
 * 3、logb^a = d 的时间复杂段： O(N^d * logN)
 *
 * 所以本类中递归时间复杂的为 log2^2 = 1 而 O(N^d = 0) 所以 1 > 0 满足第一种的时间复杂度 O(n) 这种复杂读的是遍历所有的数组的复杂的是一样的
 */
public class GetMax {

    public static int getMax(int[] arr) {
        return getProcess(arr, 0, arr.length - 1);
    }

    /**
     * 获取大
     *
     * @param arr
     * @param l   最左边的位置
     * @param r   最右边的位置
     * @return
     */
    private static int getProcess(int[] arr, int l, int r) {
        if (l == r) {
            return arr[l];
        }
        //求mid
        int mid = l + ((r - l) >> 1);//中间位置
        System.out.println(mid);
        int leftMax = getProcess(arr, l, mid);
        int rightMax = getProcess(arr, mid + 1, r);
        return Math.max(leftMax,rightMax);//左边还是右边的最大值
    }

    public static void main(String[] args) {
        int a [] = {1,2,3,2,21,2,43,5,2};
        int max = getMax(a);
        System.out.println(max);
    }
}
