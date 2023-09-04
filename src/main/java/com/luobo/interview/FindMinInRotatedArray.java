package com.luobo.interview;

/**
 * 剑指 Offer
 * <a href="https://leetcode.cn/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/description/">11. 旋转数组的最小数字</a>
 */
public class FindMinInRotatedArray {
    public static void main(String[] args) {
        int[] nums = {3, 4, 5, 1, 2};
        System.out.println(findMinInRotatedArray(nums));
    }
    public static int findMinInRotatedArray(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int pivot = low + (high - low) / 2;
            // 将中心点与上界比较
            if (nums[pivot] < nums[high]) {
                // 如果中心点比上界小，那么最小元素在中心点左边（包括它自己）
                high = pivot;
            } else if (nums[pivot] > nums[high]) {
                // 如果中心点比上界大，那么最小元素在中心点右边（不包括它自己）
                low = pivot + 1;
            } else {
                // 如果相等，那么我们可以把`high`索引减一，来处理重复项
                high -= 1;
            }
        }
        // 最后，`low`指针将指向最小元素
        return nums[low];
    }
}
