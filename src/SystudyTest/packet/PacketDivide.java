package SystudyTest.packet;

import com.alibaba.fastjson2.JSONObject;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.Random;

/**
 * 红包均摊算法
 */
public class PacketDivide {

    /**
     * 生成红包
     *
     * @param orderId
     */
    public void genRedpack(long orderId, int redPackCount) {
        //Boolean exists = jedisUtils.exists(RedisKeys.getHbPoolKey(orderId));
        //if (!exists) {
        //根据业务规则生成红包
        //总的红包金额20元 也就是2000分
        int totalAmount = 2000;
        int[] redpacks = doPartitionRedpack(totalAmount, redPackCount);
        String[] list = new String[redpacks.length];
            //将生成的红包push到redis中
        for (int i = 0; i < redpacks.length; i++) {
            //红包ID
            JSONObject object = new JSONObject();
            object.put("hbId", i);
            //红包金额,存的是分
            object.put("amount", redpacks[i]);
            list[i] = object.toJSONString();
        }
        // jedisUtils.lpush(RedisKeys.getHbPoolKey(orderId), list);
        //}
    }

    /**
     * 划分红包 * @param totalAmount 红包总额 单位：分
     *
     * @param redPackCount 红包数量
     * @return
     */
    private static int[] doPartitionRedpack(int totalAmount, int redPackCount) {
        Random random = new Random();
        //每个人至少分1分钱，2000 - 6 = 1994元 也就是要随机分的钱。
        int randomMax = totalAmount - redPackCount;
        //要把1994 随机分成6份，我们需要向1994 这个数字中插入5个点
        // 比如 6 100 500 500 1600 这5个数字把1994分成了6份：6分 94分 400分 0分 1000分 394分
        int[] posArray = new int[redPackCount - 1];
        for (int i = 0; i < posArray.length; i++) {
            int pos = random.nextInt(randomMax);
            posArray[i] = pos;
        }
        //对数组进行排序
        Arrays.sort(posArray);
        //生成红包
        int[] redpacks = new int[redPackCount];
        for (int i = 0; i <= posArray.length; i++) {
            //第一份
            if (i == 0) {
                redpacks[i] = posArray[i] + 1;
            //如果循环到posArray.length，此时数组已越界1位，randomMax - 该值 + 1分钱=最后一份
            } else if (i == posArray.length) {
                redpacks[i] = randomMax - posArray[i - 1] + 1;
            } else {
                redpacks[i] = posArray[i] - posArray[i - 1] + 1;
            }
        }
        return redpacks;
    }

    public static void main(String[] args) {
        int[] ints = doPartitionRedpack(2000, 8);
        OptionalInt reduce = Arrays.stream(ints).reduce(Integer::sum);
        int asInt = reduce.getAsInt();
        System.out.println(asInt);
        System.out.println(JSONObject.toJSONString(ints));
    }
}
