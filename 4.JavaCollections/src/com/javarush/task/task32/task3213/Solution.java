package com.javarush.task.task32.task3213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

/* 
Шифр Цезаря
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor Dpljr");
        System.out.println(decode(reader, -3));  //Hello Amigo
    }

    public static String decode(StringReader reader, int key) throws IOException {
        if (reader == null)
            return new String();

        StringBuilder sb = new StringBuilder();
         int c;
         while ((c = reader.read()) != -1) {
             sb.append((char) (c + key));
         }
         return sb.toString();
    }
}
