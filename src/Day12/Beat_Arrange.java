package Day12;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Classname Beat_Arrange
 * @Description 贪心算法学习：
 * <p>
 * 题目 ：
 * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
 * 给你每一个项目开始的时间和结束的时间 。
 * 你来安排宣讲的日程，要求会议室进行的宣讲的场次最多。 返回最多的宣讲场次。
 * @Date 2021/9/30 22:27
 * @Created by ZhuBo
 */
public class Beat_Arrange {
    static class Program {
        int start; //会议开始时间
        int end; //会议结束时间
    }


    /**
     * @param programs 会议的数组，也可以理解成，还没有被安排的会议的“容器”，存储所有的会议
     * @param done     已经完成的会议
     * @param timeLine 当前时间
     *
     *
     * @return
     */
    public static int process(Program[] programs, int done, int timeLine) {
        /**
         * 问题：这个判断可以满足所有的条件吗？它可以结束递归吗 ？
         * 答案：是的 。理由:其中存在一个隐藏的BaseCase  ==>  也就是当数组中的所有的开始时间都小于当前时间，那么当前
         * 这个递归的“分支”就会返回目前的 可以完成的 会议 这也就是 第 38 行为什么max 默认是 "done" 的 原因。
         * 目的就是在均不满足会议的时候，返回可以完成的会议数量为初始的“done” 的原因
         */
        if (programs.length == 0) {
            return done;
        }
        int max = done;//记录所有可以完成的会议的个数
        /** 开始暴力枚举 */
        for (int i = 0; i < programs.length; i++) {
            if (programs[i].start > timeLine) {//次处表示:如果当前的会议的的开始时间大于当前时间的,那么这个会议是可以被安排的
                Program[] next = copyButExcept(programs, i);//把当前的会议从“容器”中移除掉
                max = Math.max(max, process(next, done + 1, programs[i].end));//继续递归执行，再去新数组中寻找可以安排的会议
            }
        }
        return max;
    }

    /**
     * 根据下标输出对应节点
     *
     * @param programs
     * @param i
     * @return
     */
    private static Program[] copyButExcept(Program[] programs, int i) {
        Program[] array = new Program[programs.length - 1];
        int index = 0;
        for (int k = 0; k < programs.length; k++) {
            if (k != i) {
                array[index++] = programs[k];
            }
        }
        return array;
    }


   /**************************** 贪心算法的实现 ****************************/

    /**
     * 贪心算法逻辑
     * 根据会议结束时间来排序，结束时间早的先安排 ，条件和暴力枚举一样，比较每一个会议的开始时间[start]是否大于当前时间[timeLine]
     * @param programs
     * @return
     */
   public static int processByComparator (Program[] programs){
       Arrays.sort(programs);//按照比较器进行排序
       int timeLine = 0;
       int done = 0 ;
       for (int i = 0; i < programs.length; i++) {
           if (programs[i].start >= timeLine){
               timeLine = programs[i].end;
               done ++;
           }
       }
       return done;
   }




    /**
     * 实现比较器
     * 根据会议结束时间来排序，结束时间早的先安排
     * 问题： 当前贪心 是否满足所有的条件呢 ？？
     *
     */
    public static class ProgramComparator implements Comparator<Program>{

        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }


}
