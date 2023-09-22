package com.luobo.algorithm.leetcode.array;

/**
 * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii/">123. 买卖股票的最佳时机 III</a>
 */
public class MaxProfit3 {
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

        int firstBuy = Integer.MIN_VALUE, firstSell = 0;
        int secondBuy = Integer.MIN_VALUE, secondSell = 0;

        for (int price : prices) {
            firstBuy = Math.max(firstBuy, -price);
            firstSell = Math.max(firstSell, firstBuy + price);
            secondBuy = Math.max(secondBuy, firstSell - price);
            secondSell = Math.max(secondSell, secondBuy + price);
        }
        return secondSell;
    }

    public static void main(String[] args) {
        int[] nums = {3, 3, 5, 0, 0, 3, 1, 4};
        MaxProfit3 maxProfit3 = new MaxProfit3();
        System.out.println(maxProfit3.maxProfit2(nums));
    }
}
