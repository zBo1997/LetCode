package SystudyTest;

import java.util.ArrayList;
/**
 * @Classname Test
 * @Description 子线程共享父线程的参数
 * @Date 2021/7/26 11:32
 * @Created by ZhuBo
 */
public class Test2 {

   public static void main(String[] args) throws InterruptedException {
       ArrayList<String> arrayList = new ArrayList<>(20);
       arrayList.add("123");
       arrayList.add("13");
       arrayList.add("12");
       arrayList.add("12");
       arrayList.forEach(x -> {
           if(x.equals("123")){
               arrayList.remove(x);
           }
       });
   }
}
