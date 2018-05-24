package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        for (int i = 0; i < 8; i++) {
            int c = 0;

            if (i == 0 || i == 3 || i == 6)
                c = randomizer(10, 48);
            else if (i == 1 || i == 4 || i == 7)
                c = randomizer(25, 65);
            else if (i == 2 || i == 5)
                c = randomizer(26, 97);
            baos.write(c);
        }
        return baos;
    }

    public static int randomizer(int boarder, int lower) {
        return new Random().nextInt(boarder) + lower;
    }
}