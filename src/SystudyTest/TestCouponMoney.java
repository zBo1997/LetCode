
package SystudyTest;

import com.alibaba.fastjson2.JSON;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;




/**
 * 订单优惠均摊算法
 * 由于小数精度问题导致金额分配不完全
 * 订单总金额为				a
 * 订单明细金额为			b1,b2,b3....
 * a=b1+b2+...
 * 订单使用总优惠券金额为		c
 * 订单明细使用优惠券金额为	d1,d2,d3...
 * c=d1+d2...
 * <p>
 * 正常算法为
 * 明细金额占比为 			b1/a
 * 优惠券分摊金额为			d1=a*(b1/a)
 * d1=（1/3）无限循环的小数导致订单明细优惠金额与优惠金额有出入
 * <p>
 * 解决思路
 * d1=a*(b1/a)
 * d1 末位小数0舍1进
 * 例如	0.11 ->0.12
 * 0.10->0.10
 * d2=(c-d1)*(b2/(a-b1))
 * d2 末位小数0舍1进
 * 依次类推...
 *
 */
public class TestCouponMoney {


    /**
     * 订单明细分摊金额
     * @param list
     * @param couponMoney
     */
    public static void sf(List<BigDecimal> list, BigDecimal couponMoney) {
        //明细总和
        BigDecimal sum = BigDecimal.ZERO;
        Optional<BigDecimal> reduce = list.stream().reduce(BigDecimal::add);
        if (reduce.isPresent()) {
            sum = reduce.get();
        }
        for (BigDecimal oneMoney : list) {
            //单个明细分配的优惠金额
            BigDecimal lastMoney = BigDecimal.ZERO;
            if (couponMoney.compareTo(BigDecimal.ZERO) == 0) {
                System.out.println(lastMoney);
                continue;
            }
            //优惠券金额大于等于合计金额
            if (couponMoney.compareTo(sum) > -1) {
                lastMoney = oneMoney;
                System.out.println(lastMoney);
                continue;
            }
            //舍0进1
            //单个明细金额占比
            BigDecimal oneMoneyScope = oneMoney.divide(sum, 2, BigDecimal.ROUND_HALF_UP);
            //分配优惠券金额按比例 优惠金额末位舍0进1
            lastMoney = oneMoneyScope.multiply(couponMoney).setScale(2, BigDecimal.ROUND_HALF_UP);
            //如果分配比例大于金额 则等于金额
            if (lastMoney.compareTo(oneMoney) == 1) {
                lastMoney = oneMoney;
            }
            System.out.println(lastMoney);
            //优惠金额去除本次优惠金额
            couponMoney = couponMoney.subtract(lastMoney);
            //总金额减去本次商品金额
            sum = sum.subtract(oneMoney);
        }
        System.out.println("===>" + JSON.toJSON(list));
    }

    public static void main(String[] args) {
        ArrayList<BigDecimal> moneyData = new ArrayList<>();
        moneyData.add(new BigDecimal("1"));
        moneyData.add(new BigDecimal("1"));
        moneyData.add(new BigDecimal("1"));

        BigDecimal couponMoney = new BigDecimal("0.5");
        sf(moneyData,couponMoney);
    }
}
