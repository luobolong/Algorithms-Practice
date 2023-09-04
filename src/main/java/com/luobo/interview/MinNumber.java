package com.luobo.interview;

import java.util.Arrays;

public class MinNumber {
    public static void main(String[] args) {
        int[] nums = {1, 10, 55, 0, 0};
        System.out.println(minNumber(nums));
    }

    public static String minNumber(int[] nums) {
        String[] strNums = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strNums[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(strNums, (o1, o2) -> (o1 + o2).compareTo(o2 + o1));

        StringBuilder sb = new StringBuilder();
        for (String s :
                strNums) {
            sb.append(s);
        }

        while (sb.length() > 1 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }
}
