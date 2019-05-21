package com.javarush.task.task22.task2211;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/* 
Смена кодировки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        InputStream is = new FileInputStream(args[0]);
        OutputStream os = new FileOutputStream(args[1]);

        byte[] buffer = new byte[is.available()];
        is.read(buffer);
        os.write(new String(buffer, "windows-1251").getBytes(StandardCharsets.UTF_8));

        is.close();
        os.close();
    }
}