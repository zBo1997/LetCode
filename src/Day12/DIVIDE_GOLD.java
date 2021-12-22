package Day12;

import java.util.PriorityQueue;

/**
 * @Classname DIVIDE_GOLD
 * @Description 金条划分问题 （使用小根堆实现 哈夫曼树）
 * 问题描述
 * 假设将一根金条切成两半，是需要花费和金条长度相同的钱。比如，一根80cm的金条，切成两半，需要花80块钱。
 * 现在，一群人想分一整块金条，每个人分得的长度保存在一个数组中，例如[10，20，30]意思就是将一根60cm的金条，划分成10，20，30三段，请问，
 * 怎样切分最省钱？
 * 例如：
 * 先将金条切分成10和50，需要60块钱，然后，再将50切分成20和30，需要50块，总共需要110块钱。
 * 又或者，先将金条切分成30和30，花60块钱，然后再将30切分成10和20，花30块钱，总共就是90块钱。
 * 90<110，所以第二种划算。
 * <p>
 * 思路分析
 * 首先，观察题目，可以发现，每切一次，只能切出来一段最终结果，另一端肯定还有继续切割，这一段最终结果是不需要再次切分了，
 * 也就说明，后面切分时就不需要再算这一段的钱了，因此，这一段最终结果的数值越大，总的代价就越小。
 * 也就是说，我们先把大的切出来，越省钱，大的不会再次计算价钱了，越小的，我们最后切出来。
 * 因此，从上面分析出来的结果可以看出，其实，本质就是，减少数值大的遍历次数，增加数值小的遍历次数，这样总体代价最低。
 * <p>
 * 哈夫曼树
 * 这样的贪心算法有一种数据结构能够轻松实现，那就是哈夫曼树，哈夫曼树的构建过程，就是先把最小的两个相连，然后组成一个新节点，
 * 然后放入总的集合中，
 * 再从中找出最小的两颗子树再次相连，再将结果放入集合中，最终集合中只剩下一棵树，这棵树就是哈夫曼树。这道题其实就是哈夫曼树的应用。
 * <p>
 * 小根堆
 * 此题用哈夫曼树解决，为什么会提到小根堆呢？因此，哈夫曼树的构建过程，每次需要将两个子树合成的结果再次放入集合中，然后，再从集合中取出两个最小值。
 * 这个过程，如果用常规数组的话，每次放入后，是不是还需要再次排序呀？这个再次排序的过程，是不是小根堆效率很好啊？只需要调整小根堆就行了，最多logn，
 * 如果是一般方法，是不是每次都需要重新排序，效率极低的。
 * <p>
 * 最终解题思路
 * 先将数组构建成小根堆，然后，从小根堆中弹出两个。把这两个加在总和money上，然后这两个数相加，相加后，再次入小根堆。然后再从中弹出两个最小值，
 * 重复上述操作，直到小根堆中只剩一个数，结束，计算money。
 * @Date 2021/10/5 14:45
 * @Created by ZhuBo
 */
public class DIVIDE_GOLD {

    /**
     * 暴力递归
     * @param arr
     * @param pre
     * @return
     */
    public static int process(int[] arr, int pre) {
        if (arr.length == 1) {
            return pre;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                ans = Math.min(ans, process(copyAndMergerTwo(arr,i,j),pre + arr[i] + arr[j]));
            }
        }
        return ans;
    }

    public static int[] copyAndMergerTwo(int[] arr, int i, int j) {
        int[] ans = new int[arr.length - 1];
        int ansi = 0;
        for (int arri = 0; arri < arr.length; arri++) {
            if (arri != i && arri != j){
                ans[ansi ++] = arr[arri];
            }
        }
        ans[ansi] = arr[i] + arr[j];
        return ans;
    }

    public static void main(String[] args) {
        //堆的手法
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
        int[] fenshu = {10, 20, 30};
        int sum = 0;
        for (int i : fenshu) {
            heap.add(i);
        }
        while (heap.size() > 1) {
            //每一次弹出 两个数 和成一个树 重新放入小根堆中去
            int temp = heap.poll() + heap.poll();
            sum = sum + temp;
            heap.add(temp);
        }
        System.out.println(sum);
    }


}
