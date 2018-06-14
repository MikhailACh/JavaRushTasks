package com.javarush.task.task36.task3605;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.TreeSet;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        TreeSet<Character> letters = new TreeSet<>();

        int t;
        while ((t = reader.read()) != -1) {
            if (Character.isLetter(t)) {
                letters.add(Character.toLowerCase((char) t));
            }
        }
        reader.close();

        if (letters.size() > 5) {
            Iterator<Character> iterator = letters.iterator();
            for (int i = 0; i < 5; i++) {
                if (iterator.hasNext())
                    System.out.print(iterator.next());
            }

        } else {
            for (Character c : letters)
                System.out.print(c);
        }
    }
}
