package com.javarush.task.task19.task1926;

/* 
Перевертыши
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader;
        try{
            String filename = br.readLine();
            br.close();
            reader= new BufferedReader(new FileReader(filename));

            while (reader.ready()) {
                String line = reader.readLine();
                System.out.println(new StringBuilder(line).reverse().toString());
            }
            reader.close();
        } catch (IOException ioe) {ioe.getCause();}

    }
}
