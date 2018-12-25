package com.javarush.task.task39.task3901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 
Уникальные подстроки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please input your string: ");
        String s = bufferedReader.readLine();

        System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
    }

    public static int lengthOfLongestUniqueSubstring(String s) {
        if (s == null || s.isEmpty())
            return 0;

        int size = 1;

        Set<Character> uniqueString = new HashSet<>();
        List<Integer> sizes = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);

            if (!uniqueString.contains(current)) {
                uniqueString.add(current);
            } else {
                sizes.add(uniqueString.size());
                uniqueString.clear();
                uniqueString.add(current);
            }
        }
        sizes.add(uniqueString.size());

        return Collections.max(sizes);
    }
}