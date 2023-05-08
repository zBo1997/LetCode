
package JvmStudyTest.Nio;

import com.alibaba.fastjson2.JSON;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * 应用的自测 与 学习
 *
 */
public class ReferenceTest {


    public static WeakReference<String> weakReference1;

    public static void main(String[] args) {

        test1();
        //test1外部，hello对象作用域结束，没有强引用指向"value"了。只有一个弱引用指向"value"
        System.out.println("未进行gc时，只有弱引用指向value内存区域：" + weakReference1.get());

        //此时gc时会回收弱引用
        System.gc();

        //此时输出都为nuill
        System.out.println("进行gc时，只有弱引用指向value内存区域：" + weakReference1.get());

    }

    public static void test1() {
        //hello对象强引用"value"
        String hello = new String("value");

        //weakReference1对象弱引用指向"value"
        weakReference1 = new WeakReference<>(hello);

        //在test1内部调用gc，此时gc不会回收弱引用，因为hello对象强引用"value"
        System.gc();
        System.out.println("进行gc时，强引用与弱引用同时指向value内存区域：" + weakReference1.get());

    }
}
