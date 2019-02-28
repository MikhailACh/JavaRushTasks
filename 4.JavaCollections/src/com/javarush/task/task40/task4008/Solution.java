package com.javarush.task.task40.task4008;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Locale;

/* 
Работа с Java 8 DateTime API
*/

public class Solution {
    public static void main(String[] args) {
        try {
            printDate("9.10.2017 5:56:45");
            System.out.println();
            printDate("21.4.2014");
            System.out.println();
            printDate("17:33:40");
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
    }

    public static void printDate(String date) throws DateTimeParseException {
        LocalDate localDate = null;
        LocalTime localTime = null;
        DateTimeFormatter timeFormatter = null;
        DateTimeFormatter dateFormatter = null;

        if (date.contains(".") && !date.contains(":")) {
            dateFormatter = DateTimeFormatter.ofPattern("d.M.y");
            localDate = LocalDate.parse(date, dateFormatter);
        }

        if (date.contains(":") && ! date.contains(".")) {
            timeFormatter = DateTimeFormatter.ofPattern("H:m:s");
            localTime = LocalTime.parse(date, timeFormatter);
        }

        if (date.contains(":") && date.contains(".")) {
            String[] current = date.split(" ");

            timeFormatter = DateTimeFormatter.ofPattern("H:m:s");
            dateFormatter = DateTimeFormatter.ofPattern("d.M.y");
            localDate = LocalDate.parse(current[0], dateFormatter);
            localTime = LocalTime.parse(current[1], timeFormatter);
        }

        if (dateFormatter != null)
            System.out.println(
                    "День: " + localDate.getDayOfMonth() + "\n" +
                            "День недели: " + localDate.getDayOfWeek().getValue() + "\n" +
                            "День месяца: " + localDate.getDayOfMonth() + "\n" +
                            "День года: " + localDate.getDayOfYear() + "\n" +
                            "Неделя месяца: " + localDate.get(WeekFields.of(Locale.getDefault()).weekOfMonth()) + "\n" +
                            "Неделя года: " + localDate.get(WeekFields.of(Locale.getDefault()).weekOfYear()) + "\n" +
                            "Месяц: " + localDate.getMonth().getValue() + "\n" +
                            "Год: " + localDate.getYear());

        if (timeFormatter != null)
            System.out.println(
                    "AM или PM: " + (localTime.get(ChronoField.AMPM_OF_DAY) == 0 ? "AM" : "PM") + "\n" +
                            "Часы: " + localTime.get(ChronoField.HOUR_OF_AMPM) + "\n" +
                            "Часы дня: " + localTime.getHour() + "\n" +
                            "Минуты: " + localTime.getMinute() + "\n" +
                            "Секунды: " + localTime.getSecond());
    }
}