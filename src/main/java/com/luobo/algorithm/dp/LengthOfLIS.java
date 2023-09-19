package com.luobo.algorithm.dp;

import java.util.Arrays;

/**
 * leetcode 300
 * <a href="https://leetcode.cn/problems/longest-increasing-subsequence/">最长递增子序列</a>
 */
public class LengthOfLIS {
    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lengthOfLIS(nums));
    }

    public static int lengthOfLIS(int[] nums) {
        int res = 1;
        int n = nums.length;

        // dp[i] 用于存储以 nums[i] 结尾的最长递增子序列的长度。
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    // 如果nums[i] < nums[j]，则 nums[i] 可以接在以 nums[j] 结尾的递增子序列之后形成一个新的递增子序列
                    // 所以，dp[i] 可能会变为 dp[j] + 1。
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
