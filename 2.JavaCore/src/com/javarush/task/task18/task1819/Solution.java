package com.javarush.task.task18.task1819;

/* 
Объединение файлов
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String filename1 = br.readLine();
            String filename2 = br.readLine();

            //создаем два потока чтения для файла 1 и 2 и один поток записи для файла 1
            BufferedWriter fw = new BufferedWriter(new FileWriter(filename1, true));
            BufferedReader fr1 = new BufferedReader(new FileReader(filename1));
            BufferedReader fr2 = new BufferedReader(new FileReader(filename2));

            //читаем содержимое исходных файлов и пишем его в StringBuilder
            try {
                String s1, s2;
                StringBuilder sb = new StringBuilder();

                while ((s2 = fr2.readLine()) != null)
                    sb.append(s2);

                while ((s1 = fr1.readLine()) != null)
                    sb.append(s1);

                //записываем в первый файл все прочитанное
                fw.write(sb.toString());
            } finally {
                fr1.close();
                fr2.close();
                fw.close();
            }
        } catch (IOException ioe) {
            ioe.getMessage();
        }
    }
}
