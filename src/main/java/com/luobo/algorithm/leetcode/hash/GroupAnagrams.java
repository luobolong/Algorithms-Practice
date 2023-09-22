package com.luobo.algorithm.leetcode.hash;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/group-anagrams/description/">49. 字母异位词分组</a>
 */
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str :
                strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortedString = String.valueOf(chars);
            map.computeIfAbsent(sortedString, k -> {
                List<String> stringList = map.getOrDefault(sortedString, new ArrayList<>());
                res.add(stringList);
                return stringList;
            }).add(str);
        }
        return res;
    }

    public List<List<String>> groupAnagrams2(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str :
                strs) {
            int[] count = new int[26];
            for (int i = 0; i < str.length(); i++) {
                count[str.charAt(i) - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (count[i] != 0) {
                    sb.append('a' + i);
                    sb.append(count[i]);
                }
            }
            String key = sb.toString();
            map.computeIfAbsent(key, k -> {
                List<String> stringList = map.getOrDefault(key, new ArrayList<>());
                res.add(stringList);
                return stringList;
            }).add(str);
        }
        return res;
    }

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        GroupAnagrams groupAnagrams = new GroupAnagrams();
        List<List<String>> lists = groupAnagrams.groupAnagrams(strs);
        System.out.println(lists);
    }
}
