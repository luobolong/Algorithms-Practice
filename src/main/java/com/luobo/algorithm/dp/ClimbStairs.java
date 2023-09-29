package com.luobo.algorithm.dp;

public class ClimbStairs {
    public static void main(String[] args) {
        System.out.println(taijie(3));
        System.out.println(taijie(4));
    }
    public static int taijie(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1];
    }
}
