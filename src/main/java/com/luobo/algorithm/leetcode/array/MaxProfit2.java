package com.luobo.algorithm.leetcode.array;

/**
 * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/?envType=study-plan-v2&envId=top-interview-150">买卖股票的最佳时机 II</a>
 */
public class MaxProfit2 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0 || prices.length == 1) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            int sub = prices[i] - prices[i - 1];
            if (sub > 0) {
                res += sub;
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
        if (prices == null || prices.length == 0 || prices.length == 1) {
            return 0;
        }
        int n = prices.length;

        int buy = -prices[0];
        int sell = 0;

        for (int i = 1; i < n; i++) {
            sell = Math.max(sell, buy + prices[i]);
            buy = Math.max(buy, sell - prices[i]);
        }
        return sell;
    }

    public static void main(String[] args) {
        int[] nums = {7, 1, 5, 3, 6, 4};
        MaxProfit2 maxProfit2 = new MaxProfit2();
        System.out.println(maxProfit2.maxProfit2(nums));
    }
}
