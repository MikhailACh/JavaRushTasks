package com.javarush.task.task20.task2026;

import java.util.ArrayList;

/*
Алгоритмы-прямоугольники
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a = new byte[][]{
                {0, 0, 0, 0, 1, 1, 0},
                {1, 1, 0, 0, 1, 1, 0},
                {1, 1, 0, 0, 1, 1, 0},
                {1, 1, 0, 1, 0, 0, 0},
                {1, 1, 0, 1, 0, 0, 1},
                {1, 1, 0, 1, 0, 0, 1}
        };
        int count = getRectangleCount(a);
        System.out.println("count = " + count + ". Должно быть 4");
    }

    public static int getRectangleCount(byte[][] a) {
        int counter = 0;

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] == 1 && i != a.length - 1 && j != a[i].length - 1) {
                    if (a[i][j+1] == 0 && a[i+1][j] == 0)
                        counter++;
                } else if (a[i][j] == 1 && i == a.length - 1 && j != a[i].length - 1) {
                    if (a[i][j+1] == 0)
                        counter++;
                } else  if (a[i][j] == 1 && i != a.length - 1 && j == a[i].length - 1) {
                    if (a[i+1][j] == 0)
                        counter++;
                } else if (a[i][j] == 1 && i == a.length - 1 && j == a[i].length - 1) {
                    counter++;
                }
            }
        }
        return counter;
    }
}
