package Day12;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Classname BEST_IPO
 * @Description 贪心算法 ：
 * 最好的项目策划：
 * 输入： 参数1，正数数组costs 参数2，正数数组profits 参数3，正数k 参数4，正数m
 * costs[i]表示i号项目的花费 profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润) k表示你不能并行、只能串行的最多做k个项目 m表示你初始的资金
 * 说明：你每做完一个项目，马上获得的收益，可以支持你去做下一个 项目。
 * <p>
 * 解决方案 ：
 * 当你目前持有的资金有限去做那个项目最挣钱 ？
 * ==> 当然是做项目最赚钱的哪一个
 * <p>
 * 输出： 你最后获得的最大钱数
 * @Date 2021/10/5 17:14
 * @Created by ZhuBo
 */
public class BEST_IPO {

    /**
     * 使用一个结构
     * p：为这个项目的利润
     * c: 为这个项目的花费
     */
    static class Node {
        public int p;

        public int c;

        public Node(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    //创建分别 根据 花费的 不同比较器 （升序）也就是按照花费按照小根堆排序
    public static class MinCostComparator implements Comparator<Node> {
        @Override
        public int compare(Node t2, Node t1) {
            return t1.c - t2.c;
        }
    }

    //创建分别 根据 利润的 不同比较器 (倒序) 也就是按照利润按照大根堆拍戏
    public static class MaxProfitComparator implements Comparator<Node> {
        @Override
        public int compare(Node t2, Node t1) {
            return t2.p - t1.p;
        }
    }

    /**
     * @param k       可以接的项目的个数
     * @param W       初始资金
     * @param profits 数组：标识每一个项目的利润
     * @param capital 数组：标识每个项目的花费
     * @return
     */
    public static int beatIPo(int k, int W, int[] profits, int[] capital) {
        PriorityQueue<Node> nodeByMinCost = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Node> nodeByMaxProfit = new PriorityQueue<>(new MaxProfitComparator());
        for (int i = 0; i < profits.length; i++) {
            nodeByMinCost.add(new Node(profits[i],capital[i]));//初始化结构
        }
        for (int i = 0; i < k; i++) {
            //若干当前的所有项目不为空 并且 当先手中的钱可以 继续进行下一个利润最高的项目
            //把这个项目放入到
            while (!nodeByMinCost.isEmpty() && nodeByMinCost.peek().c < W){
                nodeByMaxProfit.add(nodeByMinCost.poll());
            }
            //如果没有适合的最大利润的项目，那么直接返回当前利润
            if (nodeByMaxProfit.isEmpty()){
                return W;
            }
            W += nodeByMaxProfit.poll().p;
        }
        return W;
    }
}
