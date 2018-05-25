package com.javarush.task.task19.task1911;

/* 
Ридер обертка
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        // создали переменную для хранения начального значения переменной System.out
        PrintStream original = System.out;
        // создаем выходной поток в виде массива переменной длины
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // создаем еще один поток выода в консоль
        PrintStream ps = new PrintStream(baos);
        // устанавливаем новое знвчение переменной System.out равным выходному потоку вывода в консоль,
        // который выдает в эту консоль содержимое динамического массива типа ByteArrayOutputStream
        System.setOut(ps);

        // инициируем печать некоего текста в консоль
        testString.printSomething();
        // записываем в строковую переменную
        String s = baos.toString().toUpperCase();

        System.setOut(original);
        System.out.println(s);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}
