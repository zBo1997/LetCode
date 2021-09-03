package Day5;

import java.util.Stack;

/**
 * @Classname Solution
 * @Description 验证回文串
 * @Date 2021/8/22 14:10
 * @Created by ZhuBo
 */
class Solution {
    /**
     * 
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        if(s == null){
            return false;
        }
        s = s.replaceAll("[\\pP\\p{Punct}]","").replaceAll(" ","").toLowerCase();
        Stack<Character> stack = new Stack<>();
        System.out.println(s);
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            stack.push(charArray[i]);
        }
        for (int i = 0; i < charArray.length; i++) {
            if(stack.pop() != charArray[i]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean palindrome = solution.isPalindrome("race a car");
        System.out.println(palindrome);
    }
}