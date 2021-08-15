package Day2;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * @Classname HashMapAndSortMap
 * @Description 哈希表学习
 * @Date 2021/8/15 16:10
 * @Created by ZhuBo
 */
public class HashMapAndSortMap {

    public static void main(String[] args) {
        HashMap hashMap = new HashMap();//UnSortMap
        TreeMap treeMap = new TreeMap();//sortMap

        Integer a = 127;
        Integer b = 127;
        System.out.println(a == b);

        Integer e = 128;
        Integer f = 128;
        System.out.println(e == f);

        Long c = 127L;
        Long d = 127L;
        System.out.println(c == d);
    }
}
