package com.javarush.task.task19.task1925;

/* 
Длинные слова
*/

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));
        StringBuilder sb = new StringBuilder();


        while (reader.ready()) {
            String[] line = reader.readLine().split(" ");
            for (String temp : line) {
                if (temp.length() > 6) {
                    sb.append(temp + ",");
                }
            }
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        writer.write(sb.toString());
        reader.close();
        writer.close();
    }
}
