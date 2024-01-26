package unSafe;


import sun.misc.Unsafe;

/**
 * 直接分配内存测试
 * @author zhubo
 * @since 2023/09/10
 */
public class AllocateMemoryTest {

  private static final int _1MB = 1024 * 1024;
  private static final int _1GB = 1024 * 1024 * 1024 * 1;

  public static void main(String[] args) throws Exception {
    Unsafe unsafe = UnsafeUtil.get();
    long l = unsafe.allocateMemory(_1GB);
    byte aByte = unsafe.getByte(l);
    System.out.println(l);
    System.out.println(aByte);
  }

}
