package com.javarush.task.task19.task1914;

/* 
Решаем пример
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream ps = System.out;

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream prStr = new PrintStream(bytes);
        System.setOut(prStr);

        testString.printSomething();
        int result = 0;
        String[] s = bytes.toString().split(" ");

        int a = Integer.parseInt(s[0]);
        int b = Integer.parseInt(s[2]);

        switch (s[1]) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
        }

        System.setOut(ps);

        System.out.println(a + " " + s[1] + " " + b + " = " + result);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

