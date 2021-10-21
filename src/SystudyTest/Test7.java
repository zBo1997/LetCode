package SystudyTest;

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
