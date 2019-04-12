package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string) {
        if (string == null)
            throw new TooShortStringException();

        String[] parts = string.split(" ");
        StringBuilder sb;
        try {
            sb = new StringBuilder().append(parts[1]).append(" ").append(parts[2]).append(" ").append(parts[3]).append(" ").append(parts[4]);
        } catch (IndexOutOfBoundsException iobe) {
            throw new TooShortStringException();
        }
        return sb.toString();
    }

    public static class TooShortStringException extends RuntimeException {
    }
}
