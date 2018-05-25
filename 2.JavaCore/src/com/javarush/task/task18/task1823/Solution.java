package com.javarush.task.task18.task1823;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/* 
Нити и байты
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String file;
            while (!"exit".equals(file = br.readLine()))
                new ReadThread(file).start();

            br.close();
        } catch (IOException ioe) {ioe.getMessage();}
    }

    public static class ReadThread extends Thread {
        private String fileName;

        public ReadThread(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public void run() {
            TreeMap<Integer, Integer> bytesCounter = new TreeMap<>();

            try {
                ArrayList<Integer> bytes = new ArrayList();
                FileInputStream file = new FileInputStream(fileName);

                while (file.available() > 0) {
                    bytes.add(file.read());
                }

                file.close();
                System.out.println(bytes.toString());

                for (int i = 0; i < 256; i++) {
                    int counter = 0;
                    for (int n = 0; n < bytes.size(); n++) {
                        if (i == bytes.get(n))
                            counter++;
                    }
                    bytesCounter.put(i, counter);
                }

                Map.Entry< Integer, Integer > maxEntry = null;
                for (Map.Entry< Integer, Integer > entry : bytesCounter.entrySet())
                {
                    if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
                        maxEntry = entry;
                }
                System.out.println(maxEntry.getKey() + "=" + maxEntry.getValue());
                synchronized (resultMap) {
                    resultMap.put(this.fileName, maxEntry.getKey());}
            } catch (IOException ioe) {ioe.getMessage();}
        }
    }
}
