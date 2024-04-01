package JvmStudyTest.Nio;

public class StringIntern2 {

  /**
   * 此方法证明了
   * 如果把Xmx(最大堆内存设置为6MB) 会出现 GC overhead limit exceeded
   * at java.util.HashMap.newNode(HashMap.java:1750)
   * 
   * @param args
   */
  public static void main(String[] args) {

    String str1 = new StringBuilder("ja").append("va").toString();
    String str2 = new StringBuilder("计算机").toString();
    String str3 = str2 + "等级";

    // https://www.zhihu.com/question/51102308/answer/124441115 解释了为什么 "java" 被加载了
    System.out.println(str1.intern() == str1);
    System.out.println(str2.intern() == str2);
    // JDK8 以后设置”首次遇到原则“
    System.out.println(str3.intern() == str3);

    TestCalzzInit a = new TestSubCalzzInit();
  }
}
