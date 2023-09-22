package com.luobo.algorithm.leetcode.array;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/?envType=study-plan-v2&envId=top-interview-150">121. 买卖股票的最佳时机</a>
 */
public class MaxProfit {
    /**
     * 暴力法
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int res = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                res = Math.max(profit, res);
            }
        }
        return res;
    }

    /**
     * 动态规划
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;

        int prev = 0;
        int cur = prev;
        int minPrice = prices[0];
        for (int i = 1; i < n; i++) {
            minPrice = Math.min(prices[i], minPrice);
            cur = Math.max(prev, prices[i] - minPrice);
            prev = cur;
        }
        return cur;
    }

    public static void main(String[] args) {
        int[] nums = {7, 1, 5, 3, 6, 4};
        MaxProfit maxProfit = new MaxProfit();
        System.out.println(maxProfit.maxProfit2(nums));
    }
}
