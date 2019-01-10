package com.javarush.task.task39.task3908;

import java.util.HashMap;
import java.util.Map;

/*
Возможен ли палиндром?
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(isPalindromePermutation("ateneta"));
    }

    public static boolean isPalindromePermutation(String s) {
        if (s.length() == 0 || s.length() == 1)
            return true;

        Map<Character, Integer> frequencies = new HashMap<>();
        char[] str = s.replaceAll("\\W", "").toLowerCase().toCharArray();

        for (int i = 0;  i < str.length; i++) {
            char c = str[i];
            if (!frequencies.keySet().contains(c)) {
                frequencies.put(c, 1);
            } else {
                int value = frequencies.get(c);
                frequencies.put(c, ++value);
            }
        }

        int counter = 0;
        for (Map.Entry<Character, Integer> pair : frequencies.entrySet()) {
            //System.out.println(pair.getKey() + " " + pair.getValue());
            if (pair.getValue() % 2 != 0)
                counter++;
            if (counter > 1)
                return false;
        }

        return true;
    }
}