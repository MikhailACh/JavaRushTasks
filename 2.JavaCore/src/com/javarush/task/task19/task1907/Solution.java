package com.javarush.task.task19.task1907;

/* 
Считаем слово
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Solution {
    public static void main(String[] args) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] content = null;
        StringBuilder words = new StringBuilder();

        try{
            String filename = bf.readLine();
            bf.close();
            FileReader fr = new FileReader(filename);

            while (fr.ready())
                words.append((char)fr.read());

            // конвертируем полученный StringBuilder в String, разделяя его по
            // признаку наличия любых знаков, отличных от букв или цифр
            content = words.toString().split("\\W");

            int counter = 0;
            for (String s : content) {
                if (s.equals("world")) counter++;
            }

            fr.close();
            System.out.println(counter);
        } catch (IOException ioe) {ioe.getMessage();}
    }
}
