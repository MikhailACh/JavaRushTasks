package com.javarush.task.task39.task3904;

import java.util.ArrayList;
import java.util.List;

/* 
Лестница
*/
public class Solution {
    private static int n = 70;
    public static void main(String[] args) {
        System.out.println("Number of possible runups for " + n + " stairs is: " + countPossibleRunups(n));
    }

    public static long countPossibleRunups(int n) {
        if (n < 0)
            return 0;

        switch (n) {
            case 0: return 1;
            case 1: return 1;
            case 2: return 2;
        }

        List<Long> runups = new ArrayList<>();
        runups.add(0, 1L);
        runups.add(1, 1L);
        runups.add(2, 2L);

        for (int i = 3; i <= n; i++) {
            runups.add(runups.get(i - 1) + runups.get(i - 2) + runups.get(i - 3));
        }

        return runups.get(runups.size() - 1);
    }
}