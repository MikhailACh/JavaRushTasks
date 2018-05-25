package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws ParseException{
        BufferedReader br;
        Date date;
        String name;
        ArrayList<String> lines = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy");

        try {
            br = new BufferedReader(new FileReader(args[0]));

            while (br.ready()) {
                String line = br.readLine();
                if (line.startsWith("\ufeff"))
                    lines.add(line.substring(1));
                else lines.add(line);
            }
            br.close();
        } catch (FileNotFoundException fnfe) {
            fnfe.getMessage();
        } catch (IOException ioe) {
            ioe.getMessage();
        }

        for (String s : lines) {
            String[] arr = s.split(" ");
            int l = arr.length;
            name = "";

            for (int i = 0; i < l-3; i++)
                name += (arr[i] + " ");
            date = format.parse(arr[l-3] + " " + arr[l-2] + " " + arr[l-1]);
            PEOPLE.add(new Person(name.trim(), date));
        }
    }
}
