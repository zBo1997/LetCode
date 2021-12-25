package Day12;


import java.util.HashMap;
import java.util.List;

/**
 * 使用并查集解决 3个不同账户的 相同用户
 */
public class MergeUsers {

    public static class User{
        public String a;
        public String b;
        public String c;

        public User(String a, String b, String c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }


    }

    /***
     * 使用并查集进行合并，最后取到 并查集的的个数，就是相同用户的数量
     * （1，10，13） （2，10，37） （400，500，37）
     * @param userList
     * @return
     */
    public static int mergeUser(List<User> userList){
        Union_Set.UnionSet<User> union_set = new Union_Set.UnionSet<>(userList);
        HashMap<String, User> mapA = new HashMap<>();
        HashMap<String, User> mapB = new HashMap<>();
        HashMap<String, User> mapC = new HashMap<>();
        for (User user : userList) {
            if (mapA.containsKey(user.a)){
                union_set.union(user,mapA.get(user.a));
            } else {
                mapA.put(user.a,user);
            }
            if (mapB.containsKey(user.b)){
                union_set.union(user,mapA.get(user.b));
            } else {
                mapB.put(user.b,user);
            }
            if (mapC.containsKey(user.c)){
                union_set.union(user,mapA.get(user.c));
            } else {
                mapC.put(user.c,user);
            }

        }

        return union_set.getSizeNum();
    }
}
