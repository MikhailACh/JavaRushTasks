package com.javarush.task.task32.task3201;

import java.io.*;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) {
        String filename = args[0];
        int number = Integer.parseInt(args[1]);
        String text = args[2];

        try (RandomAccessFile raf = new RandomAccessFile(filename, "rw")) {
            long newNumber = raf.length();
            if (newNumber < number) {
                raf.setLength(newNumber + text.getBytes().length);
                raf.seek(newNumber);
            } else raf.seek(number);

            raf.write(text.getBytes());
        } catch (FileNotFoundException fnfe) {
            System.out.println("Файл не найден!");
        } catch (IOException ioe) {
            System.out.println("I/O Exception!");
        }
    }
}
