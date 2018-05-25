package com.javarush.task.task18.task1826;

/* 
Шифровка
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        switch (args[0]) {
            case "-e" :
                writer(args[0], encrypter(charReader(args[1])));
                break;
            case "-d" :
                writer(args[0], decrypter(byteReader(args[1])));
                break;
        }
    }

    static String charReader(String filename) {
        int r;
        StringBuilder sb = new StringBuilder();
        BufferedReader br;
        try{
             br = new BufferedReader(new FileReader(filename));

             while ((r = br.read()) > 0)
                 sb.append(r);
             br.close();
        } catch (IOException ioe) {
            ioe.getMessage();
        }
        return sb.toString();
    }

    static byte[] byteReader(String filename) {
        byte[] buffer;
        BufferedInputStream bis;

        try{
            bis = new BufferedInputStream(new FileInputStream(filename));
            buffer = new byte[bis.available()];
            bis.read(buffer, 0, bis.available());
            bis.close();
        } catch (IOException ioe) {
            System.out.println("File is empty!");
            return null;
        }
        return buffer;
    }

    static void writer(String fileOutputName, byte[] sb) {
        BufferedOutputStream bos;
        try{
            bos = new BufferedOutputStream(new FileOutputStream(fileOutputName));
            bos.write(sb);
            bos.close();
        } catch (FileNotFoundException ioe) {
            ioe.getMessage();
        } catch (IOException ioe) {
            ioe.getMessage();
        }
    }

    static void writer(String fileOutputName, String sb) {
        BufferedWriter bw;
        try{
            bw = new BufferedWriter(new FileWriter(fileOutputName));
            bw.write(sb.toString());
            bw.close();
        } catch (FileNotFoundException ioe) {
            ioe.getMessage();
        } catch (IOException ioe) {
            ioe.getMessage();
        }
    }

    private static byte[] encrypter(String sb) {
        byte[] string;

        try{
            if (!sb.isEmpty())
                string = sb.getBytes("UTF-8");
            else {
                System.out.println("File is empty!");
                string = null;}
        } catch(UnsupportedEncodingException uee) {
            System.out.println("Encrypting error!");
            string = null;
            }
        return string;
    }

    private static String decrypter(byte[] sb) {
        String s;
        try{
            s = new String(sb, "UTF-8");
        } catch (UnsupportedEncodingException uee) {
            System.out.println("Decrypting error!");
            s = null;
        }
        return s;
    }
}
