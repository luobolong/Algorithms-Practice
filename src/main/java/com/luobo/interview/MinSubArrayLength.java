package com.luobo.interview;

/**
 * leetcode 209
 * <a href="https://leetcode.cn/problems/minimum-size-subarray-sum/description/">最小长度的连续子数组</a>
 */
public class MinSubArrayLength {
    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 2, 4, 3, 7};
        int target = 7;
        System.out.println(minSubarrayLen(target, nums));
    }

    public static int minSubarrayLen(int target, int[] nums) {
        int minLength = Integer.MAX_VALUE;

        int left = 0;
        int sum = 0;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            // 当窗口和大于等于目标时
            while (sum >= target) {
                // 更新最小子数组长度
                minLength = Math.min(minLength, right - left + 1);

                // 从窗口和中减去左指针指向的元素，并移动左指针
                sum -= nums[left];
                left++;
            }
        }

        // 如果找不到这样的子数组，返回0，否则返回最小长度
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}
