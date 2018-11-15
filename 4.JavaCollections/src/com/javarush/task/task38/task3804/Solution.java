package com.javarush.task.task38.task3804;

/* 
Фабрика исключений
*/

import static com.javarush.task.task38.task3804.ExceptionApplicationMessage.UNHANDLED_EXCEPTION;
import static com.javarush.task.task38.task3804.ExceptionDBMessage.NOT_ENOUGH_CONNECTIONS;

public class Solution {
    public static Class getFactoryClass() {
        return ExceptionFactory.class;
    }

    public static void main(String[] args) {
    }
}