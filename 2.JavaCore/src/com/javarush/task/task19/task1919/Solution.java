package com.javarush.task.task19.task1919;

/* 
Считаем зарплаты
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) {
        BufferedReader fr;
        TreeMap<String, Double> map = new TreeMap<>();

        try{
            fr = new BufferedReader(new FileReader(args[0]));

            while (fr.ready()) {
                String line = fr.readLine();
                String[] s = line.split(" ");
                if (s[0].startsWith("\ufeff"))
                    s[0] = s[0].substring(1);
                if (map.containsKey(s[0]))
                    map.put(s[0], Double.parseDouble(s[1]) + map.get(s[0]));
                else map.put(s[0], Double.parseDouble(s[1]));
            }
            fr.close();

            for (Map.Entry<String, Double> pair : map.entrySet())
                System.out.println(pair.getKey() + " " + pair.getValue());
        } catch (IOException ioe) {ioe.getMessage();}
    }
}
