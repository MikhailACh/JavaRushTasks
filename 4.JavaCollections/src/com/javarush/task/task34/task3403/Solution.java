package com.javarush.task.task34.task3403;


/*
Разложение на множители с помощью рекурсии
*/
public class Solution {
    public void recursion(int n) {
        int i = 2;
        if (n <= 1) return;
        while (i <= n){
            if (n % i == 0) {
                System.out.print(i + " ");
                if (i == n)
                    return;
                break;
            }
            i++;
        }
        recursion(n / i);
    }


    public static void main(String[] args) {
        new Solution().recursion(378);
    }
}