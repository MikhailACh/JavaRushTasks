package com.javarush.task.task16.task1630;

import java.io.*;

public class Solution {
    public static String firstFileName;
    public static String secondFileName;

    //add your code here - добавьте код тут
    static {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            firstFileName = br.readLine();
            secondFileName = br.readLine();
            br.close();
        }
        catch (IOException i) {}
    }
    public static void main(String[] args) throws InterruptedException {
        systemOutPrintln(firstFileName);
        systemOutPrintln(secondFileName);
    }

    public static void systemOutPrintln(String fileName) throws InterruptedException {
        ReadFileInterface f = new ReadFileThread();
        f.setFileName(fileName);
        f.start();
        //add your code here - добавьте код тут
        f.join();
        System.out.println(f.getFileContent());
    }

    public interface ReadFileInterface {

        void setFileName(String fullFileName);

        String getFileContent();

        void join() throws InterruptedException;

        void start();
    }

    //add your code here - добавьте код тут
    public static class ReadFileThread  extends Thread implements ReadFileInterface {
        BufferedReader reader;
        String line;
        StringBuilder builder = new StringBuilder();

        public void setFileName(String fullFileName) {
            try {reader = new BufferedReader(new InputStreamReader(new FileInputStream(fullFileName),"windows-1251"));}
            catch (IOException fnfe) {fnfe.getMessage();}
        }

        public String getFileContent() {
            return builder.toString();
        }

        public void run() {
            try {
                while ((line = reader.readLine()) != null) {
                    builder.append(line + " ");
                }
                reader.close();
            } catch (IOException ioe) {
                System.out.println("ioe:" + ioe.getMessage());}
        }
    }
}
