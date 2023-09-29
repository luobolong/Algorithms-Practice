package com.luobo.algorithm.leetcode.array;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/leetbook/read/top-interview-questions-easy/x2gy9m/">删除排序数组中的重复项</a>
 */
public class RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return nums.length;
        }
        int left = 0;
        for (int right = 1; right < nums.length; right++) {
            if (nums[left] != nums[right]) {
                left++;
                nums[left] = nums[right];
            }
        }
        return left + 1;
    }

    public static void main(String[] args) {
        RemoveDuplicates p01RemoveDuplicates = new RemoveDuplicates();
        int[] nums = {1, 2, 3};
        int res = p01RemoveDuplicates.removeDuplicates(nums);
        System.out.println(res);
        System.out.println(Arrays.toString(nums));
    }
}
