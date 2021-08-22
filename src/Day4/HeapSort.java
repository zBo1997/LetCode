package Day4;

/**
 * @Classname HeapSort
 * @Description 堆排序
 * @Date 2021/8/22 14:10
 * @Created by ZhuBo
 */
public class HeapSort {
    /**
     * 按照堆的定义进行 排序
     * 1、先调整成大根堆
     * 2、从上大小的方法 也就是hearpInsert事件复杂度 O(N * logn)
     * 3、从下到上的方法，时间复杂度为O(n)
     * 4、然后把把堆已经heapify的数组中的，也是”堆化“好的数据中的最大值和和末尾交换，然后减少堆的小之后，再去调整堆
     * 一直周而复始，直到heapSize < 0 排序完成 时间阀组的为（N * logN）
     * @param arr
     */
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //逐渐线下沉
        for (int i = arr.length; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }
        int heapSize = arr.length;
        swap(arr,0,--heapSize);
        while(heapSize > 0){
            heapify(arr,0,heapSize);
            swap(arr,0,--heapSize);
        }
    }

    private static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;//左节点
        while (index < heapSize) {
            // 两个孩子中，谁的值大，把下标给largest
            // 1）只有左孩子，left -> largest
            // 2) 同时有左孩子和右孩子，右孩子的值<= 左孩子的值，left -> largest
            // 3) 同时有左孩子和右孩子并且右孩子的值> 左孩子的值， right -> largest
            int largest = left + 1 > heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            // 父和较大的孩子之间，谁的值大，把下标给largest 充分考虑父节点的大小
            largest = arr[index] > arr[largest] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(arr,largest,index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    /**
     * 交换的放方法
     *
     * @param arr
     * @param i
     * @param j
     */
    private static void swap(int[] arr, int i, int j) {
        //防止相同的数字异或成 “0”
        if (i == j) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
