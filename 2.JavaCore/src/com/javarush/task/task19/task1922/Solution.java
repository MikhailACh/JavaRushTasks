package com.javarush.task.task19.task1922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Ищем нужные строки
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader filereader= new BufferedReader(new FileReader(br.readLine()));

        br.close();

        while (filereader.ready()) {
            String line = filereader.readLine();

            int count = 0;
            for (String s : words) {
                if (line.contains(s)) {
                    count += 1;
                }
            }
            if (count == 2)
                System.out.println(line);
        }
        filereader.close();

    }
}
