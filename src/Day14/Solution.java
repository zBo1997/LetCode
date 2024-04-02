package Day14;

//给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
//
//你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
//
//返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0
/**
 * @author zhubo
 */
public class Solution {
    public static int maxProfit(int[] prices) {
        //最小的售价
        int minPrice = Integer.MAX_VALUE;
        //最大利润
        int maxProfit = 0;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else {
                maxProfit = prices[i] - minPrice;
            }
        }
        return maxProfit;
    }


    public static void main(String[] args) {
        int[] test = {7,6,4,3,1};

        System.out.println(maxProfit(test));
    }
}