package com.javarush.task.task40.task4012;

import java.time.*;
import java.time.temporal.ChronoUnit;

/* 
Полезные методы DateTime API
*/

public class Solution {
    public static void main(String[] args) {
        getPeriodBetween(LocalDate.of(1908, 3, 23), LocalDate.now(Clock.systemDefaultZone()));
        addTime(LocalTime.now(), 7, ChronoUnit.HOURS);
    }

    public static boolean isLeap(LocalDate date) {
        return date.isLeapYear();
    }

    public static boolean isBefore(LocalDateTime dateTime) {
        return dateTime.isBefore(LocalDateTime.now());
    }

    public static LocalTime addTime(LocalTime time, int n, ChronoUnit chronoUnit) {
        return time.plus(n, chronoUnit);
    }

    public static Period getPeriodBetween(LocalDate firstDate, LocalDate secondDate) {
        return firstDate.isBefore(secondDate)
                ? Period.between(firstDate, secondDate)
                : Period.between(secondDate, firstDate);
    }
}