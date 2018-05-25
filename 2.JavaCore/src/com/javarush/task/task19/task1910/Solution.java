package com.javarush.task.task19.task1910;

/* 
Пунктуация
*/

import java.io.*;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        try{
            String filename1 = bf.readLine();
            String filename2 = bf.readLine();
            bf.close();

            BufferedReader reader = new BufferedReader(new FileReader(filename1));
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename2));

            String s;
            while (reader.ready()) {
                s = reader.readLine();
                System.out.println(s);
                writer.write(s.replaceAll("[\\W\\n]", ""));
            }

            reader.close();
            writer.close();
        } catch (IOException ioe) {ioe.getMessage();}
    }
}
