package com.javarush.task.task39.task3913;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Test {
    public static void main(String[] args) {
        String s = "05.01.2021 20:22:55";
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        try {
            Date date = format.parse(s);
            System.out.println(date + " " + date.getTime());
        } catch (ParseException pe) {
            pe.getCause();
        }

        System.out.println(Event.WRITE_MESSAGE.name() + " = " + Event.WRITE_MESSAGE.toString());

        String query = "get ip for user = \"Eduard Petrovich Morozko\" and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59″";
        String query2 = "get ip for user = \"Eduard Petrovich Morozko″";
        String value = query.substring(query.indexOf(" \"") + 2, query.length() - 65);
        String[] values = query.split("\"");
        System.out.println(value);
        System.out.println("----------------------------------------------------------------");
        for (String ss : values) {
            System.out.println(ss);
        }
        System.out.println("----------------------------------------------------------------");
        System.out.print(values[1] + " " + values[3] + " " + values[5].replaceAll("″", ""));
        System.out.println(query2.split("\"")[1].replaceAll("″", ""));
    }
}
