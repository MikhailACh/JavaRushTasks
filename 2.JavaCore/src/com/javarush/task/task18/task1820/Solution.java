package com.javarush.task.task18.task1820;

/* 
Округление чисел
*/

import java.io.*;
import java.util.ArrayList;


public class Solution {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String filename1 = br.readLine();
        String filename2 = br.readLine();

        BufferedReader fr = new BufferedReader(new FileReader(filename1));
        FileWriter fw = new FileWriter(filename2);

        StringBuilder sb = new StringBuilder();
        String s;
        while ((s = fr.readLine()) != null) {
            if (s.startsWith("\uFEFF")) s = s.substring(1);
            sb.append(s);
        }

        ArrayList<String> numbers = new ArrayList();
        for (String z : sb.toString().split(" "))
        {
            float x = Float.parseFloat(z);
            numbers.add(Math.round(x) + " ");
        }

        for (String v : numbers)
            fw.append(v);

        br.close();
        fw.close();
    }
}
