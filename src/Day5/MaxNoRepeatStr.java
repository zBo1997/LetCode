package Day5;

/**
 * @Classname TineTree
 * @Description 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度 todo 未完成
 * @Date 2021/8/22 14:10
 * @Created by ZhuBo
 */
public class MaxNoRepeatStr {

    public static void main(String[] args) {
        int abcabcbb = lengthOfLongestSubstring("pwwkew");
        System.out.println(abcabcbb);
    }

    public static int lengthOfLongestSubstring(String s) {
        int[] index = new int[26];
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int i1 = chars[i] - 'a';
            index[i1] ++ ;
        }
        int j = 0;
        for (int i = 0; i < index.length; i++) {
            if(index[i] != 0){
                j++;
            }
        }
        return j;
    }
}
