package com.javarush.task.task13.task1319;

import java.io.*;

/* 
Запись в файл с консоли
*/

public class Solution {

    public static void main(String[] args) throws IOException
    {

        BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new FileWriter(br.readLine()));

        String text;

        do
        {
            text= br.readLine();
            bw.write(text);
            bw.newLine();
        }
        while(!text.equals("exit"));

        br.close();
        bw.close();
    }
}
