package Day12;

import java.util.HashSet;

/**
 * @Classname Light
 * @Description 贪心算法学习：
 * <p>
 * 题目 ：街灯问题
 * 给定一个字符串，字符串中只包含“X”或者是“.” ，其中“.” 是需要安装路灯的，“X“是不能安装路灯的，
 * 每一个路灯的照射范围是 这个路灯的前后各一个未知，
 * 求出放置最少的路灯的方案，并且个路灯的个数。
 * </p>
 * @Date 2021/9/30 22:27
 * @Created by ZhuBo
 */
public class Light {
    /**
     *暴力 开始方法
     * @param road
     * @return
     */
    public static int minLight(String road){
        if (road == null || road.length() == 0){
            return 0;
        }
        return process(road.toCharArray(),0,new HashSet<>());
    }

    /**
     * 暴力方法，列出所有的可能性
     * @param str 字符串
     * @param index 当前下标
     * @param lights 需要放入路灯的下标位置
     * @return
     */
    public static int process(char[] str , int index, HashSet<Integer> lights ){
        //字符串走完的时候，我们去统计HashSet中所有所有的街灯
        if(index == lights.size()){ //这是结束的时候
            for (int i = 0; i < str.length; i++) {
                if (str[i] != 'X'){//看当前这个“.”的卫视需要放灯
                    if (!lights.contains( i -  1 ) && !lights.contains( i ) && !lights.contains( i + 1)){
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lights.size();
        } else {
            //当且节点不需放灯
            int no = process(str,index + 1,lights);
            //当前节点需要放灯
            int yes = Integer.MAX_VALUE;
            if (str[index]  == '.'){//只有“.” 是可以放灯的
                lights.add(index);
                yes = process(str,index + 1,lights);
                lights.remove(index);//因为 使用了 老的 HashSet 所以要恢复现场 , 因为 每一次 回溯时候 都
            }
            return Math.min(yes,no);
        }
    }


    /**
     * 贪心算法解决实现
     * 每一次都是一个最好的答案
     * @param str 路灯字符串
     * @return
     */
    public static int process(String str){
        char[] chars = str.toCharArray();
        int index = 0 ; //当前数组的下表
        int light = 0; //当前的街道的灯数
        while (index < chars.length){
            if (chars[index] == 'X'){//当前未知不能安装路灯
                index ++ ;
            } else {//否则是肯定可以装置路灯，只不过需要确定安装路灯的位置
                light ++; //路灯数量+1
                if (chars[index + 1] == 'X'){//如果说下一个位置不能放路灯，就去看再下一个位置
                    index = index + 2 ;
                } else {//否则说明下一个可以放路灯 直接去看下一个的下一个
                    index = index + 3;
                }
            }
        }
        return light;
    }

}
