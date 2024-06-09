package Day15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 异位词分组Letcode 中等难度
 */
public class GroupAnagrams {

    public static void main(String[] args) {
        String[] condition = { "eat", "tea", "tan", "ate", "nat", "bat" };
        List<List<String>> groupAnagrams = groupAnagrams(condition);
        System.out.println(groupAnagrams);
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> grouMap = new HashMap();
        for (String keywordString : strs) {
            char[] charArray = keywordString.toCharArray();
            Arrays.sort(charArray);
            String sorString = new String(charArray);
            List<String> orDefaultWordList = grouMap.getOrDefault(sorString, new ArrayList<String>());
            orDefaultWordList.add(keywordString);
            grouMap.put(sorString, orDefaultWordList);
        }
        return new ArrayList<>(grouMap.values());
    }
}
