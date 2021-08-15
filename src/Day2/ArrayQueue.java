package Day2;

/**
 * @Classname ArrayQueue
 * @Description 定量数组实现队列
 * @Date 2021/8/14 18:06
 * @Created by ZhuBo
 */
public class ArrayQueue {
    /**
     * 结构
     */
    public static class MyQueue {
        private int[] arr;
        private int push;//入队列游标
        private int poll;//出队列游标
        private int size;//存放数量
        private final int limit;//数组大小

        public MyQueue(int limit) {
            this.arr = new int[limit];
            this.push = 0;
            this.poll = 0;
            this.size = 0;
            this.limit = limit;
        }

        /**
         * 具体的Api
         */
        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("Queue is Full");
            }
            size++;//数量++
            arr[push] = value;
            push = nextIndex(push);//游标指向下一个位置
        }

        /**
         * 弹出栈
         * @return
         */
        public int pop(){
            if(isEmpty()){
                throw new RuntimeException("Queue is Empty");
            }
            size--;//数量--
            int i = arr[poll];
            poll = nextIndex(poll);
            return i;
        }

        public boolean isEmpty(){
            return arr.length == 0;
        }

        /**
         * 如果现在的下标是i 那么返回下一个
         * @param i
         * @return
         */
        private int nextIndex(int i) {
            return i < limit - 1 ? i + 1 : 0;
        }
    }
}
