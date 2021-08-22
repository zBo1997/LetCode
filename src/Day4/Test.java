package Day4;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Classname Test
 * @Description JDK内部实现的小根堆(也叫优先级队列)
 * @Date 2021/8/22 13:13
 * @Created by ZhuBo
 */
public class Test {

    public static class MyComp implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }
    public static void main(String[] args) {
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
        heap.add(1);
        heap.add(15);
        heap.add(11);
        heap.add(12);
        heap.add(17);
        heap.add(19);
        heap.forEach(x -> System.out.println(x));
    }
}
