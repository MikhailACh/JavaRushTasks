package com.javarush.task.task18.task1818;

/* 
Два в одном
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String filename1 = br.readLine();
            String filename2 = br.readLine();
            String filename3 = br.readLine();

            BufferedWriter fw = new BufferedWriter(new FileWriter(filename1, true));
            BufferedReader fr1 = new BufferedReader(new FileReader(filename2));
            BufferedReader fr2 = new BufferedReader(new FileReader(filename3));

            try{
                String s1, s2;

                while ((s1 = fr1.readLine()) != null)
                    fw.write(s1);

                while ((s2 = fr2.readLine()) != null)
                    fw.write(s2);
            } finally {
                fr1.close();
                fr2.close();
                fw.close();
            }
        } catch (IOException ioe) {ioe.getMessage();}
    }
}
