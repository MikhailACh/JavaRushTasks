package com.javarush.task.task19.task1923;

/* 
Слова с цифрами
*/

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) {
        String filename1 = args[0];
        String filename2 = args[1];
        BufferedReader reader;
        BufferedWriter writer;
        StringBuilder sb = new StringBuilder();

        Pattern numbers = Pattern.compile("^\\D*$");
        Matcher m;

        try{
            reader = new BufferedReader(new FileReader(filename1));
            writer = new BufferedWriter(new FileWriter(filename2));

            String[] lines;
            while (reader.ready()) {
                lines = reader.readLine().split(" ");
                 for (String s : lines) {
                     m = numbers.matcher(s);
                     if (!m.matches()) {
                         sb.append(s + " ");
                     }
                 }
            }

            sb.deleteCharAt(sb.lastIndexOf(" "));
            writer.write(sb.toString());
            writer.close();
            reader.close();
        } catch (IOException ioe) {ioe.getMessage();}
    }
}
