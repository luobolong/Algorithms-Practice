package com.luobo.algorithm.dp;

public class MaxGiftValue {
    public static void main(String[] args) {
        int[][] grid = {{1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}};
        System.out.println(maxGiftValue(grid));
    }

    public static int maxGiftValue(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        if (grid[0].length == 0) {
            return 0;
        }
        int rowCount = grid.length;
        int columnCount = grid[0].length;
        int[][] dp = new int[rowCount][columnCount];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < rowCount; i++) {
            dp[i][0] = grid[i][0] + dp[i - 1][0];
        }
        for (int i = 1; i < columnCount; i++) {
            dp[0][i] = grid[0][i] + dp[0][i - 1];
        }
        for (int i = 1; i < rowCount; i++) {
            for (int j = 1; j < columnCount; j++) {
                dp[i][j] = grid[i][j] + Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[columnCount - 1][rowCount - 1];
    }
}
