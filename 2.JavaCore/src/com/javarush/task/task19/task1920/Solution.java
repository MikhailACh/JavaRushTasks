package com.javarush.task.task19.task1920;

/* 
Самый богатый
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        String filename = args[0];
        BufferedReader br;
        HashMap<String, Double> salaries = new HashMap<>();
        LinkedHashSet<String> maxis = new LinkedHashSet<>();

        try {
            br = new BufferedReader(new FileReader(filename));

            String[] line;
            double d;
            while (br.ready()) {
                line = br.readLine().split(" ");

                if (line[0].startsWith("\ufeff"))
                    line[0] = line[0].substring(1);

                d = Double.parseDouble(line[1]);

                if (salaries.containsKey(line[0]))
                    salaries.put(line[0], salaries.get(line[0]) + d);
                else salaries.put(line[0], d);
            }
            br.close();
        } catch (FileNotFoundException fnfe) {fnfe.getMessage();
        } catch (IOException ioe) {ioe.getMessage();}

        double max = 0.0d;
        for (Map.Entry<String, Double> entry : salaries.entrySet()) {
            if (entry.getValue() >= max)
                max = entry.getValue();
        }
        for (Map.Entry<String, Double> entry : salaries.entrySet()) {
            if (entry.getValue() == max)
                maxis.add(entry.getKey());
        }
        for (String s : maxis)
            System.out.println(s);
    }
}
