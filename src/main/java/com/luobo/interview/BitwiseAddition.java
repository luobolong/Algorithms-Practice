package com.luobo.interview;

/**
 * 使用位运算实现加法
 */
public class BitwiseAddition {
    public static void main(String[] args) {
        int a = 3, b = 4;
        System.out.println(add(a, b));
    }

    public static int add(int a, int b) {
        // 在每次循环迭代中，a 存储了不包括进位的加法结果，而 b 存储了进位值。
        // 这个过程一直持续到没有更多的进位（即 b 变为 0）
        while (b != 0) {

            // 计算进位。当在两个数上执行加法运算时，如果两个相应的位都是1，则会产生进位。
            // 这里，(a & b) 执行按位与操作，找出哪些位在两个数中都是1。
            // 然后我们将这个结果左移一位（<< 1），这是因为进位总是影响更高一位的计算。
            int carry = (a & b) << 1;

            // 这行用于实际加法计算但不包括进位。按位异或操作（^）在两个数的相应位上进行加法，但不考虑进位，结果存储在 a 中。
            a = a ^ b;

            // 将进位值存储在 b 中，以便在下一次循环中使用。
            b = carry;
        }
        return a;
    }
}
