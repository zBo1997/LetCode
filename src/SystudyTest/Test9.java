package SystudyTest;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * @Classname Test8
 * @Description 子线程共享父线程的参数
 * @Date 2021/7/26 11:32
 * @Created by ZhuBo
 */
public class Test9 {

    public static void main(String[] args) throws InterruptedException, NoSuchFieldException {
        String order = "ds$->{1..2}.order_$->{0..255}";
        String[] orders = order.split(".order");
        String substring = orders[0].substring(6, orders[0].length()-1);
        String[] dss =  substring.split("\\.");
        System.out.println(dss.toString());

        System.out.println(50  * 1000 / 100);
    }


}
