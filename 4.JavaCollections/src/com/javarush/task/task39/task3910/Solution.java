package com.javarush.task.task39.task3910;

import static java.lang.Math.log10;

/* 
isPowerOfThree
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(isPowerOfThree(3));
        System.out.println(isPowerOfThree(5));
        System.out.println(isPowerOfThree(27));
        System.out.println(isPowerOfThree(81));
    }

    public static boolean isPowerOfThree(int n) {
        if (n <= 0)
            return false;
        if (n == 1)
            return true;

        Double d = log10(n)/log10(3);
        if (d - d.intValue() == 0)
            return true;

        return false;
    }
}
