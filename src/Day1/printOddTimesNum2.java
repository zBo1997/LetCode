package Day1;


/**
 * @Classname printOddTimesNum2
 * @Description arr,两种数，出现的奇数次数位2次，找出这俩个个数
 * @Date 2021/8/13 21:42
 * @Created by ZhuBo
 */
public class printOddTimesNum2 {

    public static void printOddTimesNum2(int[] arr){
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];// 也就是这两个数的异或后的数
        }
        //通过一种方法 使得逼出一个数的二进格式的 找出这这个位置上是1的数字
        // 这个数 取反 + 1 后进行与运算 = 这个数的 出现1 的位置
        int rightOne = eor & (~eor + 1);

        int selectOne = 0 ;
        //第二此遍历，通过
        for (int i = 0; i < arr.length; i++) {
            //查看所有数组下的数字，是否在二进制情况下，和这个最右位置1的数进行 & 运算后 此数 不为 0
            // 我们就找到了这书
            if((arr[i] & rightOne) != 0){
                selectOne = arr[i];
            }
        }

        System.out.println(selectOne + " " + (eor ^ selectOne));
    }

    public static void main(String[] args) {
        int array[] = {3,3,3,1,1,1,1,1,1,7,7,7};
        printOddTimesNum2(array);
    }
}
