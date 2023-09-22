package com.luobo.algorithm.leetcode.hash;

import java.util.HashSet;

/**
 * <a href="https://leetcode.cn/problems/longest-consecutive-sequence/?envType=study-plan-v2&envId=top-100-liked">128. 最长连续序列</a>
 */
public class LongestConsecutive {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }

        HashSet<Integer> hashSet = new HashSet<>();
        int maxCount = 0;

        for (int num : nums) {
            hashSet.add(num);
        }

        for (int num : nums) {
            hashSet.add(num);
            if (!hashSet.contains(num - 1)) {
                int count = 1;
                int temp = num + 1;
                while (hashSet.contains(temp)) {
                    count++;
                    temp++;
                }
                maxCount = Math.max(maxCount, count);
            }
        }
        return maxCount;
    }

    public static void main(String[] args) {
        int[] nums = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        LongestConsecutive longestConsecutive = new LongestConsecutive();
        System.out.println(longestConsecutive.longestConsecutive(nums));
    }
}
