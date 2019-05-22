package com.javarush.task.task22.task2212;

import java.util.HashMap;
import java.util.Map;

/*
Проверка номера телефона
*/
public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        if (telNumber == null || telNumber.length() == 0 || telNumber.length() < 10)
            return false;

        if (!telNumber.chars().noneMatch(Character::isLetter)) return false;

        if (!Character.isDigit(telNumber.charAt(telNumber.length() - 1))) return false;

        String template10 = "(\\(\\d{3}\\))?(\\d+)-?(\\d+)-?(\\d+)";
        String template12 = "\\+\\d+(\\(\\d{3}\\))?(\\d+)-?(\\d+)-?(\\d+)";
        
        if (telNumber.startsWith("+"))
            return telNumber.matches(template12);
        if (Character.isDigit(telNumber.charAt(0)) || telNumber.startsWith("("))
            return telNumber.matches(template10);

        return false;
    }

    public static void main(String[] args) {
        HashMap<String, Boolean> phones = new HashMap<>();
        phones.put("+380501234567", true);
        phones.put("+38(050)1234567", true);
        phones.put("+38050123-45-67", true);
        phones.put("050123-4567", true);
        phones.put("+38)050(1234567", false);
        phones.put("+38(050)1-23-45-6-7", false);
        phones.put("050ххх4567", false);
        phones.put("050123456", false);
        phones.put("(0)501234567", false);

        for (Map.Entry<String, Boolean> pair : phones.entrySet()) {
            if (checkTelNumber(pair.getKey()) != pair.getValue())
                System.out.println("ERROR:Should be:" + pair.getValue() + " checkTelNumber:" + checkTelNumber(pair.getKey()) + " " + pair.getKey());
        }
    }
}