package com.javarush.task.task19.task1915;

/* 
Дублируем текст
*/

import java.io.*;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream original = System.out;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);

        testString.printSomething();
        System.setOut(original);

        BufferedReader br;
        FileOutputStream bw;
        try{
            br = new BufferedReader(new InputStreamReader(System.in));
            bw = new FileOutputStream(br.readLine());
            baos.writeTo(bw);
            System.out.println(baos.toString());

            br.close();
            bw.close();
        } catch (IOException ioe) {ioe.getMessage();}
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}

