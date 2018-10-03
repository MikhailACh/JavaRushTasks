package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.javarush.task.task37.task3714.RomanFigures.*;

/* 
Древний Рим
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s) {
        char[] number = s.toCharArray();
        int result = 0;

        int length = number.length;
        for (int i = 0; i < length; i++) {
            int current = Enum.valueOf(RomanFigures.class, String.valueOf(number[i])).transform();
            boolean isFigureFound = false;

            if (i + 1 < length) {
                String str = new StringBuilder().append(number[i]).append(number[i + 1]).toString();
                for (RomanFigures r : RomanFigures.values()) {
                    if (r.toString().equals(str)) {
                        result += Enum.valueOf(RomanFigures.class, String.valueOf(str)).transform();
                        isFigureFound = true;
                        System.out.println(Enum.valueOf(RomanFigures.class, String.valueOf(str)) + " " + result);
                        i++;
                    }
                }
            }

            if (!isFigureFound)
                result += current;
        }

        return result;
    }
}