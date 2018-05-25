package com.javarush.task.task18.task1825;

import java.io.*;
import java.util.*;

/* 
Собираем файл
*/

public class Solution {
    public static void main(String[] args) {
        writer(reader());
    }
    static ArrayList<String> reader() {

        ArrayList<String> files = new ArrayList<>();
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            String filename;
            while (!"end".equals(filename = br.readLine())) {
                files.add(filename);
            }
            br.close();
        } catch (IOException ioe) {ioe.getMessage();}
        Collections.sort(files, new Comparator<String>() {
            public int compare(String o1, String o2) {
                return o1.length() - (o2.length());
            }
        });
        for (String s : files)
            System.out.println(s);
        return files;
    }
    static void writer(ArrayList<String> files) {
        BufferedOutputStream writer;
        BufferedInputStream reader;
        File file;
        String filename = files.get(0).substring(0, files.get(0).indexOf(".part"));
        try{
            file = new File(filename);
            writer = new BufferedOutputStream(new FileOutputStream(file));
            for (String s : files) {
                reader = new BufferedInputStream(new FileInputStream(s));
                while (reader.available() > 0)
                    writer.write(reader.read());
                reader.close();
            }
            writer.close();
        } catch (IOException ioe) {ioe.getMessage();}
    }
}