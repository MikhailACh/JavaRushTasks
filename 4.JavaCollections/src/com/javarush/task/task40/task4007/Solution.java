package com.javarush.task.task40.task4007;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Работа с датами
*/

public class Solution {
    /*private static Map<String, String> templates = new HashMap<>();
    static {
        templates.put("\\d{2}.\\d+.\\d{4}\\s\\d{2}:\\d{2}:\\d{2}", "dd.MM.yyyy HH:mm:ss");
        templates.put("\\d{2}.\\d+.\\d{4}", "dd.MM.yyyy");
        templates.put("\\d{2}:\\d{2}:\\d{2}", "HH:mm:ss");
    }*/

    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = null;
        boolean isDate = false, isTime = false;

        if (date.contains(".")) {
            sdf = new SimpleDateFormat("dd.MM.yyyy");
            isDate = true;
        }

        if (date.contains(":")) {
            sdf = new SimpleDateFormat("HH:mm:ss");
            isTime = true;
        }

        if (date.contains(":") && date.contains(".")) {
            sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        }

        try {
            calendar.setTime(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (isDate)
            System.out.println(
                    "День: " + calendar.get(Calendar.DATE)+ "\n" +
                    "День недели: " + (calendar.get(calendar.DAY_OF_WEEK) - 1 == 0 ? 7 : calendar.get(calendar.DAY_OF_WEEK) - 1) + "\n" +
                    "День месяца: " + calendar.get(calendar.DAY_OF_MONTH) + "\n" +
                    "День года: " + calendar.get(calendar.DAY_OF_YEAR) + "\n" +
                    "Неделя месяца: " + calendar.get(calendar.WEEK_OF_MONTH) + "\n" +
                    "Неделя года: " + calendar.get(calendar.WEEK_OF_YEAR) + "\n" +
                    "Месяц: " + (calendar.get(calendar.MONTH) + 1) + "\n" +
                    "Год: " + calendar.get(calendar.YEAR));

        if (isTime)
            System.out.println(
                    "AM или PM: " + (calendar.get(calendar.AM_PM) == 0 ? "AM" : "PM") + "\n" +
                    "Часы: " + calendar.get(calendar.HOUR) + "\n" +
                    "Часы дня: " + calendar.get(calendar.HOUR_OF_DAY) + "\n" +
                    "Минуты: " + calendar.get(calendar.MINUTE) + "\n" +
                    "Секунды: " + calendar.get(calendar.SECOND));
        /*for (Map.Entry<String, String> pair : templates.entrySet()) {
            String key = pair.getKey();
            String value = pair.getValue();

            Matcher m = Pattern.compile(key).matcher(date);
            if (m.matches()) {
                try {
                    Date current = new SimpleDateFormat(value).parse(date);
                    calendar.setTime(current);
                } catch (ParseException pe) {
                    pe.printStackTrace();
                }
            }
        }

        System.out.println("День: " + calendar.get(Calendar.DATE)+ "\n" +
                "День недели: " + (calendar.get(calendar.DAY_OF_WEEK) - 1) + "\n" +
                "День месяца: " + calendar.get(calendar.DAY_OF_MONTH) + "\n" +
                "День года: " + calendar.get(calendar.DAY_OF_YEAR) + "\n" +
                "Неделя месяца: " + calendar.get(calendar.WEEK_OF_MONTH) + "\n" +
                "Неделя года: " + calendar.get(calendar.WEEK_OF_YEAR) + "\n" +
                "Месяц: " + calendar.get(calendar.MONTH) + "\n" +
                "Год: " + calendar.get(calendar.YEAR) + "\n" +
                "AM или PM: " + (calendar.get(calendar.AM_PM) == 0 ? "AM" : "PM") + "\n" +
                "Часы: " + calendar.get(calendar.HOUR) + "\n" +
                "Часы дня: " + calendar.get(calendar.HOUR_OF_DAY) + "\n" +
                "Минуты: " + calendar.get(calendar.MINUTE) + "\n" +
                "Секунды: " + calendar.get(calendar.SECOND));*/
    }
}