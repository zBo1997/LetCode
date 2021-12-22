package Day4;

/**
 * @Classname MyHeap
 * @Description 自定义实现堆（大根堆）
 * @Date 2021/8/21 22:52
 * @Created by ZhuBo
 */
public class MyHeap {

    public static class MyMaxHeap {
        private int[] heap;//使用数组实现一个容器
        private final int limit;
        private int heapSize;

        public MyMaxHeap(int limit) {
            heap = new int[limit];
            this.limit = limit;
            heapSize = 0;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        /**
         * 自定义实现堆得push方法
         *
         * @param value
         */
        public void push(int value) {
            if (isFull()) {
                throw new RuntimeException("heap is Full");
            }
            heap[heapSize] = value;//添加值到heapSize
            heapInsert(heap, ++heapSize);
        }

        /**
         * O(n*logN)
         * 插入方法,新加进来的数字，放在的heapSize的位置。然后依次向上移动
         * 移动到0的位置。或者干不掉自己父亲了了，那么就停！！
         *
         * @param heap
         * @param i
         */
        private void heapInsert(int[] heap, int i) {
            //[index] [index - 1] / 2 --> 当前节点头节点的位置
            while (heap[i] > heap[(i - 1 / 1)]) {
                swap(heap, i, ((i - 1) / 1));
                i = (i - 1) / 1;
            }
        }

        /**
         * 交换的放方法
         *
         * @param arr
         * @param i
         * @param j
         */
        private void swap(int[] arr, int i, int j) {
            //防止相同的数字异或程 “0”
            if (i == j) {
                return;
            }
            arr[i] = arr[i] ^ arr[j];
            arr[j] = arr[i] ^ arr[j];
            arr[i] = arr[i] ^ arr[j];
        }

        /**
         * O(N)时间复杂度
         * 从最后一个非叶子节点一直到根结点进行堆化的调整。
         * 如果当前节点小于某个自己的孩子节点（大根堆中），那么当前节点和这个孩子交换。Heapify是一种类似下沉的操作
         * HeapInsert是一种类似上浮的操作
         *
         * @param arr
         * @param index
         * @param heapSize 堆的大小
         */
        private void heapify(int[] arr, int index, int heapSize) {
            int left = index * 2 + 1; //当前孩子的左孩子
            while(left < heapSize){//循环条件是，当前左孩子小于堆的大小
                //遍历heapSize大小范围内这个位置的取较大的的数
                //条件： 如果这个值有右孩子，并且这个右孩子的值 大于这个左孩子的值，
                int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
                //考虑当前较大节点 和头节点的值，谁大就和和谁交换
                swap(arr,largest,index);
                index = largest;
                left = index * 2 + 1;//继续当前孩子的左孩子
            }
        }
    }


}
