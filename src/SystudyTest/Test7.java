package SystudyTest;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 罗马数字包含以下七种字符:I，V，X，L，C，D和M。
 * <p>
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做II，即为两个并列的 1。12 写做XII，即为X+II。 27 写做XXVII, 即为XX+V+II。
 * <p>
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做IIII，而是IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为IX。这个特殊的规则只适用于以下六种情况：
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/roman-to-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Test7 {

    public static Map<Character, Integer> map = new LinkedHashMap();

    static {
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
    }

    static class Solution {
        /**
         *
         * @param s
         * @return
         */
        public int romanToInt(String s) {
            int a = 0;
            Integer start = map.get(s.charAt(0));
            for (int i = 1; i < s.length(); i++) {
                Integer cur = map.get(s.charAt(i));
                if (start < cur) {
                    a -= start;
                } else {
                    a += start;
                }
                start = cur;
            }
            a += start;
            return a;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.romanToInt("III"));
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Classname Test
 * @Description 子线程共享父线程的参数
 * @Date 2021/7/26 11:32
 * @Created by ZhuBo
 */
public class Test7 {

    static class Role {
        private String name;

        public String getName() {
            System.out.println("我是get方法");
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) throws InterruptedException, NoSuchFieldException {

        Test7.Role role = new Test7.Role();
        role.setName("zhubo");
        getXxx(role, "name");
    }

    public static <T> T getXxx(T o,String args) throws NoSuchFieldException {
        Class cls = o.getClass();
        //判断该属性是否存在
        Field field = field = cls.getDeclaredField(args);
        if(field == null){
            field = cls.getField(args);
        }
        if(field == null){
            return null;
        }


        String fieldName = "get"+args.substring(0,1).toUpperCase()+(args.length()>1?args.substring(1):"");
        Method method = null;
        try {
            method = cls.getMethod(fieldName);
            return (T)method.invoke(o);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
