package JvmStudyTest.Nio;


import java.util.HashSet;
import java.util.Set;

public class StringIntern {

    /**
     * 此方法证明了
     * 如果把Xmx(最大堆内存设置为6MB) 会出现 GC overhead limit exceeded
     * 	at java.util.HashMap.newNode(HashMap.java:1750)
     * @param args
     */
    public static void main(String[] args) {

        Set<String> set = new HashSet<>();
        long i = 0;
        while (true){
            set.add(String.valueOf(i ++).intern());
        }

    }
}
