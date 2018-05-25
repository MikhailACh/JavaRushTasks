package com.javarush.task.task19.task1908;

/* 
Выделяем числа
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] content;
        StringBuilder sb = new StringBuilder();

        try {
            String filename1 = bf.readLine();
            String filename2 = bf.readLine();
            bf.close();

            BufferedReader reader = new BufferedReader(new FileReader(filename1));
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename2));

            while (reader.ready())
                sb.append((char)reader.read());

            // конвертируем полученный StringBuilder в String, разделяя его по пробелам
            content = sb.toString().split("\\s");

            for (String s : content) {
                if ((Character.isDigit(s.charAt(0)))) // если первый символ элемента, конвертированный в char - число
                    writer.write(s + " ");
            }
            reader.close();
            writer.close();
        } catch(IOException ioe) {ioe.getMessage();}
    }
}
