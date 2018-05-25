package com.javarush.task.task18.task1812;

import java.io.*;

/* 
Расширяем AmigoOutputStream
*/

public class QuestionFileOutputStream implements AmigoOutputStream {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private AmigoOutputStream aos;

    public QuestionFileOutputStream(AmigoOutputStream aos) {
         this.aos = aos;
    }

    @Override
    public void close() {
        System.out.println("Вы действительно хотите закрыть поток? Д/Н");
        try {
            String answer = br.readLine();
            br.close();
            if (answer.equals("Д"))
                aos.close();
        } catch (IOException ioe) {
            ioe.getMessage();
        }
    }
    public void flush() {
        try {
            aos.flush();
        } catch (IOException ioe) {
            ioe.getMessage();}
    }

    public void write(int b) {
        try {
        aos.write(b);
    } catch (IOException ioe) {
        ioe.getMessage();}
    }

    public void write(byte[] b) {
        try {
            aos.write(b);
        } catch (IOException ioe) {
            ioe.getMessage();}
    }

    public void write(byte[] b, int off, int len) {try {
        aos.write(b, off, len);
    } catch (IOException ioe) {
        ioe.getMessage();}
    }
}

