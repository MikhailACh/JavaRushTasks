package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> a = new ArrayList<>();
        ArrayList<String> b = new ArrayList<>();
        BufferedReader file1, file2;

        try{
            String filename1 = reader.readLine();
            String filename2 = reader.readLine();
            reader.close();

            file1 = new BufferedReader(new FileReader(filename1));
            file2 = new BufferedReader(new FileReader(filename2));

            while (file1.ready()) {
                String s = file1.readLine();
                if (s.startsWith("\ufeff"))
                    a.add(s.substring(1));
                else a.add(s);}

            while (file2.ready()) {
                String s = file2.readLine();
                if (s.startsWith("\ufeff"))
                    b.add(s.substring(1));
                else b.add(s);}

            file1.close();
            file2.close();
        } catch (IOException ioe) {ioe.getMessage();}

        String check, s1, s2, s3;
        if (b.size() >= a.size()) {
            for (int i = 0; i < b.size(); i++) {
                s2 = b.get(i);

                if (i >= a.size()) {
                    lines.add(new LineItem(Type.ADDED, s2));
                    continue;
                }
                check = a.get(i);

                if (i + 1 < b.size()) {
                    s3 = b.get(i+1);
                } else s3 = null;

                if (i > 0) {
                    s1 = b.get(i-1);
                } else s1 = null;

                if (check.equals(s2)) {
                    lines.add(new LineItem(Type.SAME, check));
                } else if (check.equals(s3) && !check.equals(s2) ) {
                    lines.add(new LineItem(Type.ADDED, s2));
                    a.add(i, s2);
                } else if (!check.equals(s2) && !check.equals(s3) ) {
                    lines.add(new LineItem(Type.REMOVED, check));
                    b.add(i, check);
                }
            }
        }

        if (a.size() > b.size()) {
            for (int i = 0; i < a.size(); i++) {
                check = a.get(i);

                if (i >= b.size()) {
                    lines.add(new LineItem(Type.REMOVED, check));
                    continue;
                }
                s2 = b.get(i);

                if (i + 1 < b.size()) {
                    s3 = b.get(i+1);
                } else s3 = null;

                if (i > 0) {
                    s1 = b.get(i-1);
                } else s1 = null;

                if (check.equals(s2)) {
                    lines.add(new LineItem(Type.SAME, check));
                } else if (!check.equals(s2) && !check.equals(s3)) {
                    lines.add(new LineItem(Type.REMOVED, check));
                    b.add(i, check);
                } else if (!check.equals(s2) && check.equals(s3)) {
                    lines.add(new LineItem(Type.ADDED, s2));
                    a.add(i, s2);
                }
            }
        }

        for (LineItem s : lines) System.out.println(s.type + " " + s.line);
    }

    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
