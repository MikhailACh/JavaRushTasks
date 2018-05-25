package com.javarush.task.task19.task1906;

/* 
Четные символы
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String filename1, filename2;
        FileReader fr;
        FileWriter fw;

        try{
            filename1 = br.readLine();
            filename2 = br.readLine();
            br.close();

            fr = new FileReader(filename1);
            fw = new FileWriter(filename2);

            for (int i = 1; fr.ready(); i++){
                int value = fr.read();
                if (i !=0 && i%2 == 0)
                    fw.write(value);
            }

            fr.close();
            fw.close();
        } catch (IOException ioe) {
            ioe.getMessage();
        }
    }
}
