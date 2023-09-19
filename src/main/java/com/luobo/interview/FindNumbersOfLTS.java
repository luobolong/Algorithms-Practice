package com.luobo.interview;

/**
 * 最长递增子序列（LIS）的个数
 */
public class FindNumbersOfLTS {
    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 4, 7};
        System.out.println(findNumberOfLTS(nums));
    }
    private static int findNumberOfLTS(int[] nums) {
        int res = 0;
        // 当前找到的最长递增子序列的长度
        int max = 0;
        int n = nums.length;
        // dp[i] 用于保存以 nums[i] 结尾的最长递增子序列的长度
        int[] dp = new int[n];
        // count[i] 用于保存以 nums[i] 结尾的最长递增子序列的个数
        int[] count = new int[n];

        // 初始化 dp 和 count 数组
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            count[i] = 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // 对于每个小于 i 的 j，判断是否能够形成更长的递增子序列
                if (nums[i] > nums[j]) {
                    if (dp[i] == dp[j] + 1) {
                        // 找到了一个长度相同的递增子序列，增加 count[i]
                        count[i] += count[j];
                    } else if (dp[i] < dp[j] + 1) {
                        // 找到了一个更长的递增子序列，更新 dp[i] 和 count[i]
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    }
                }
            }
            // 更新全局最长递增子序列的长度和个数
            if (max == dp[i]) {
                res += count[i];
            }
            if (max < dp[i]) {
                max = dp[i];
                res = count[i];
            }
        }
        return res;
    }
}
