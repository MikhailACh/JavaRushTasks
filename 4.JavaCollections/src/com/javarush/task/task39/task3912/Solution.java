package com.javarush.task.task39.task3912;

import static java.lang.Math.min;

/* 
Максимальная площадь
*/

public class Solution {
    public static void main(String[] args) {
        int[][] matrix = {
                {1,1,1,0,0,0,0,0,0},
                {1,1,0,0,1,1,1,1,0},
                {0,0,0,0,1,1,1,1,0},
                {0,0,0,0,1,1,1,1,0},
                {0,0,0,0,1,1,1,1,0},
                {0,0,0,0,0,0,0,0,0}
        };

        System.out.println("The square is " + maxSquare(matrix));
    }

    public static int maxSquare(int[][] matrix) {
        int[][] cache = matrix.clone();
        int result = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i == 0 || j == 0)
                    continue;
                else if (matrix[i][j] == 1)
                    cache[i][j] = 1 + min(min(matrix[i - 1][j], matrix[i - 1][j - 1]), matrix[i][j - 1]);


                if (cache[i][j] > result)
                    result = cache[i][j];
            }
        }
        return result * result;
    }
}