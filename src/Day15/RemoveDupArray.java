package Day15;

/**
 *
 */
public class RemoveDupArray {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int resultOnce = removeOnce(nums);
        int resultTwice = removeTwice(nums);
        System.out.println(resultOnce);
        System.out.println(resultTwice);
    }

    /**
     * 原地移除数组中重复数字 出现一次 双指针
     *
     * @param array
     * @return
     */
    public static int removeOnce(int[] array) {
        int p = 0;
        int q = 1;

        while (q < array.length) {
            if (array[p] != array[q]) {
                array[p + 1] = array[q];
                p++;
            }
            q++;
        }
        return p + 1;
    }

    /**
     * 变种原地移除数组中重复数字 出现两次【快慢指针实现】
     *
     * @param array
     * @return
     */
    public static int removeTwice(int[] array) {

        int p = 2;
        int q = 2;

        //和上面思路一样，如果没有到数组末尾，一直向后推移
        while (q < array.length){
            if (array[p - 2] != array[q]){
                array[p] = array[q];
                p ++;
            }
            q++;
        }
        return p;
    }
}
