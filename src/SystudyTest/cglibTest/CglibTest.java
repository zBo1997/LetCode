package SystudyTest.cglibTest;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

public class CglibTest {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(QService.class);
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
            System.out.println("my callback");
            methodProxy.invokeSuper(o, objects);
            return o;
        });
        QService qService = (QService) enhancer.create();
        System.out.println("测试test1方法 --------");
        qService.test1();
        System.out.println("测试test2方法---------");
        qService.test2();
        System.out.println("getClass方法---------");
        qService.getClass();
        System.out.println("hashCode方法--------");
        //qService.hashCode();
    }
}

class QService {
    public void test1() {
        System.out.println("test1");
    }

    public void test2() {
        System.out.println("test2");
    }
}