package com.javarush.task.task19.task1924;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Замена чисел
*/

public class Solution {
    public static Map<Integer, String> map = new HashMap<Integer, String>();

    static {
        map.put(0, "ноль");
        map.put(1, "один");
        map.put(2, "два");
        map.put(3, "три");
        map.put(4, "четыре");
        map.put(5, "пять");
        map.put(6, "шесть");
        map.put(7, "семь");
        map.put(8, "восемь");
        map.put(9, "девять");
        map.put(10, "десять");
        map.put(11, "одиннадцать");
        map.put(12, "двенадцать");
    }

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String filename;
        BufferedReader reader;
        ArrayList<String> lines = new ArrayList<>();

        try{
            filename = br.readLine();
            br.close();
            reader = new BufferedReader(new FileReader(filename));

            while (reader.ready())
                lines.add(reader.readLine());
            reader.close();
        } catch (IOException ioe) {ioe.getMessage();}

        for (String line : lines) {
            for (Map.Entry<Integer, String> pair : map.entrySet()) {
                String temp1 = String.valueOf(pair.getKey());
                String temp2 = pair.getValue();

                line = line.replaceAll("\\b" + temp1 + "\\b", temp2);
            }
            System.out.println(line);
        }
    }
}
