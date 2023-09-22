package com.luobo.algorithm.leetcode.array;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iv/">188. 买卖股票的最佳时机 IV</a>
 */
public class MaxProfit4 {
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length <= 1 || k == 0) {
            return 0;
        }

        int[] buys = new int[k];
        Arrays.fill(buys, Integer.MIN_VALUE);
        int[] sells = new int[k];

        for (int price : prices) {
            buys[0] = Math.max(buys[0], -price);
            sells[0] = Math.max(sells[0], buys[0] + price);
            for (int j = 1; j < k; j++) {
                buys[j] = Math.max(buys[j], sells[j - 1] - price);
                sells[j] = Math.max(sells[j], buys[j] + price);
            }
        }
        return sells[k - 1];
    }

    public static void main(String[] args) {
        MaxProfit4 maxProfit4 = new MaxProfit4();
        int[] prices = {1, 2};
        System.out.println(maxProfit4.maxProfit(1, prices));
    }
}
