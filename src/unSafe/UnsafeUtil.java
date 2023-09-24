package unSafe;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

public class UnsafeUtil {

    public static Unsafe get() throws Exception {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        return (Unsafe) field.get(null);
    }
}
