package SystudyTest;


import com.alibaba.fastjson2.JSONObject;
import org.bytedeco.javacpp.Loader;

import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * @Classname Test11
 * @Description
 * @Date 2021/7/26 11:32
 * @Created by ZhuBo
 */
public class Test11 {

    /**
     *
     * @param f1
     * @param f2
     * @return
     */
    public static int[] unionSortList(int[] f1, int[] f2) {
        int indexM = 0;
        int indexN = 0;
        int cur = 0;
        int[] result = new int[f1.length + f2.length];
        while (indexM < f1.length && indexN < f2.length) {
            if (f1[indexM] <= f2[indexN]) {
                result[cur] = f1[indexM];
                indexM++;
            } else {
                result[cur] = f2[indexN];
                indexN++;
            }
            cur++;
        }
        if (indexM != f1.length) {
            for (int i = indexM; i < f1.length; i++) {
                result[cur] = f1[i];
                cur++;
            }
        } else {
            for (int i = indexN; i < f2.length; i++) {
                result[cur] = f2[i];
                cur++;
            }
        }
        return result;
    }



    public static void main(String[] args) {
        int[] f1 = new int[]{1, 2, 3};
        int[] f2 = new int[]{2, 3, 4};
        System.out.println(JSONObject.toJSONString(unionSortList(null, null)));
    }
}
