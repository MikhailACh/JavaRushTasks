package com.javarush.task.task19.task1918;

/* 
Знакомство с тегами
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) {
        BufferedReader br;
        String filename;

        BufferedReader reader;
        StringBuilder sb = new StringBuilder();

        try{
            br = new BufferedReader(new InputStreamReader(System.in));
            filename = br.readLine();
            reader = new BufferedReader(new FileReader(filename));

            while (reader.ready())
                sb.append(reader.readLine());

            br.close();
            reader.close();
        } catch (IOException ioe) {ioe.getMessage();}

        String html = sb.toString().replaceAll("/r/n", "");

        String patternStart = "<" + args[0];
        String patternEnd = "</" + args[0] + ">";
        Pattern starting_Tag = Pattern.compile(patternStart);
        Pattern ending_Tag = Pattern.compile(patternEnd);

        Matcher m = starting_Tag.matcher(html);
        Matcher n = ending_Tag.matcher(html);

        TreeMap<Integer, String> all = new TreeMap<>();
        TreeMap<Integer, Integer> indexes = new TreeMap<>();

        while (m.find())
            all.put(m.start(), patternStart);

        while (n.find())
            all.put(n.end() - 1, patternEnd);

        Stack<Integer> AA = new Stack<>();
        Stack<Integer> BB = new Stack<>();

        for (Map.Entry<Integer, String> pair : all.entrySet()) {
            if (pair.getValue() == patternStart) {
                AA.push(pair.getKey());
            } else if(pair.getValue() == patternEnd) {
                BB.push(pair.getKey() + 1);
                indexes.put(AA.pop(), BB.pop());
            }
        }

        for (Map.Entry<Integer, Integer> pair : indexes.entrySet())
            System.out.println(html.substring(pair.getKey(), pair.getValue()));
    }
}