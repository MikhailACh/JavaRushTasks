package com.javarush.task.task19.task1909;

/* 
Замена знаков
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        try{
            String filename1 = bf.readLine();
            String filename2 = bf.readLine();
            bf.close();

            BufferedReader reader = new BufferedReader(new FileReader(filename1));
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename2));

            String element;
            while (reader.ready()) {
                element = reader.readLine().replaceAll("\\.", "!");
                writer.write(element);
            }
            reader.close();
            writer.close();
        } catch (IOException ioe) {ioe.getMessage();}
    }
}