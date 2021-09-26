package Day12;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

/**
 * @Classname LowestLexicography
 * @Description 贪心算法 字典序排序算法
 * @Date 2021/9/26 22:27
 * @Created by ZhuBo
 */
public class LowestLexicography {

    /**
     * 暴力递归，字典序排序
     *
     * @param args 所有等待排序的字符串
     * @param used 已经被使用的一个“容器”，如果这个字符串已经被 used 注册过了，当前args的数据不能再使用 [这里存入的是下标]
     * @param all  收集所有拼接的字符串
     * @param path 拼接的字符串
     */
    public static void process(String[] args, HashSet<Integer> used, ArrayList<String> all, String path) {
        if (used.size() == args.length)
        /** 当前已经使用过的字符串的“容器“ 大小 说明所有的字符已经被使用过了*/ {
            all.add(path);
        } else {
            for (int i = 0; i < args.length; i++) {
                if (used.contains(i)) {
                    used.add(i);//这里标识一个字符分支的开始 ，每次都添加一个被注册的字符
                    process(args, used, all, path + args[i]);//每个分支的递归
                    used.remove(i);//当一字符分支的结束 ，需要把容器清空
                }
            }
        }
    }

    /**
     * 一个含有字典序的排序 比较器
     */
    public static class MyComparator implements Comparator<String> {

        /**
         * String 字典序排序
         *
         * @param a
         * @param b
         * @return
         */
        @Override
        public int compare(String a, String b) {
            return (a + b).compareTo(b + a);
        }
    }

    public static String process(String[] strs) {
        if (strs.length < 0) {
            return "";
        }
        ArrayList<String> all = new ArrayList<>();
        HashSet<Integer> used = new HashSet<>();
        process(strs, used, all, "");//从一个空字符串开始添加，相当于初始化path
        String lowest = all.get(0);
        for (int i = 1; i < all.size(); i++) {
            if (all.get(i).compareTo(lowest) < 0){
                lowest = all.get(i);//比较字典序，发现更小的继续遍历查找
            }
            return lowest;
        }
        return lowest;
    }

}
