package JvmStudyTest.Nio;


public class TestSubCalzzInit extends TestCalzzInit {

  static int x = 5;

  public TestSubCalzzInit() {
    System.out.println("TestSubCalzzInit");
  }

  static {
    System.out.println("TestSubCalzzInit static");
  }

  public void calc(){
    x += 10;
    System.out.println(x);
  }

  static {
    x /= 3;
  }

  public static void main(String[] args) {
    int x = 5 +  args.length ;
    System.out.println(x > 4 ? 99.99 :9);
    TestSubCalzzInit testSubCalzzInit = new TestSubCalzzInit();
    testSubCalzzInit.calc();
  }
}
