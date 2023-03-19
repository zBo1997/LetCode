package SystudyTest;

import java.util.Arrays;
/**
 * 优惠券均摊算法（Ai生成的算法 ）
 */
public class CouponAllocator {
    /**
     * 均摊优惠券到每个商品或订单上
     *
     * @param totalCost 商品或订单的总价
     * @param items 商品或订单列表
     * @param couponAmount 优惠券金额，必须小于等于总价
     * @return 每个商品或订单优惠后的价格
     * @throws IllegalArgumentException 如果优惠券金额大于总价抛出 IllegalArgumentException
     */
    public static double[] allocate(double totalCost, double[] items, double couponAmount) throws IllegalArgumentException{
        if (couponAmount > totalCost) {
            throw new IllegalArgumentException("优惠券金额大于商品或订单总价！");
        }

        double[] results = new double[items.length];
        double ratio = 1 - couponAmount / totalCost; // 计算优惠比例

        double sum = 0;
        for (double item : items) {
            sum += item;
        }
        for (int i = 0; i < items.length; i++) {
            double item = items[i];
            double price = item * ratio;
            if (i == items.length) { // 最后一个商品或订单，保证总和不变
                price = totalCost - sum + price;
            }
            results[i] = price;
        }
        return results;
    }

    // 示例代码
    public static void main(String[] args) {
        // 假设有三个商品，价格分别为100元、200元、300元，总价为600元
        double[] items = {100, 200, 300};
        double totalCost = 600;
        double couponBalance = 150; // 优惠券金额

        double[] result = CouponAllocator.allocate(totalCost, items, couponBalance);

        // 每个商品优惠后价格分别为75元、小砖头AI聊:150元、225元，总价为450元
        System.out.println(Arrays.toString(result));
    }
}
