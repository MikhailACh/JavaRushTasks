package com.javarush.task.task18.task1816;

/* 
Английские буквы
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) {
        System.out.println(matcher(read(args[0])));
    }

    //читаем из файла filename, добавляя прочитанное в StringBuilder
    public static String read(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
                try {
                    String s;
                    while ((s = in.readLine()) != null){
                        sb.append(s);
                    }
                } finally {
                    in.close();
                }
        } catch (IOException ioe) {ioe.getMessage();}

        return sb.toString();
    }

    //ищем Matcher'ом все английские буквы в обоих регистрах в переданной строке
    public static int matcher(String source) {
        Matcher m = Pattern.compile("[a-zA-Z]").matcher(source);
        int counter = 0;

        while (m.find())
            counter++;
        return counter;
    }
}
