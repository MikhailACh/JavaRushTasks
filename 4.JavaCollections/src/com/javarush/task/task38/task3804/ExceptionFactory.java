package com.javarush.task.task38.task3804;

public class ExceptionFactory {
    static Throwable factory(Enum e) {
        if (e == null)
            return new IllegalArgumentException();

        String message = e.name().charAt(0) + e.name().substring(1).replaceAll("_", " ").toLowerCase();

        if (e instanceof ExceptionApplicationMessage)
            return new Exception(message);

        if (e instanceof ExceptionDBMessage)
            return new RuntimeException(message);

        if (e instanceof ExceptionUserMessage)
            return new Error(message);

        return new IllegalArgumentException();
    }
}