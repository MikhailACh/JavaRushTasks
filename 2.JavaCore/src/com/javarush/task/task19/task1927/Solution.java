package com.javarush.task.task19.task1927;

/* 
Контекстная реклама
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream original = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PrintStream ps = new MyStream(new PrintStream(baos));
        System.setOut(ps);

        // инициируем печать некоего текста в консоль
        testString.printSomething();

        System.setOut(original);

        System.out.println(baos.toString());
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }

    public static class MyStream extends PrintStream{
        String reclaim = "\nJavaRush - курсы Java онлайн";
        int count = 0;
        PrintStream baos;

        MyStream(PrintStream baos) {
            super(baos);
            this.baos = baos;
        }

        @Override
        public void print(String s) {
            if (count == 1) {
                baos.print(s + reclaim);
                count = 0;
            } else {
                baos.print(s);
                count++;
            }
        }
    }
}
