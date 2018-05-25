package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* 
Знакомство с properties
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    public void fillInPropertiesMap() {
        //implement this method - реализуйте этот метод
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream fis;
        String filename;
        try {
            filename = br.readLine();
            br.close();

            fis = new FileInputStream(filename);
            load(fis);
        } catch (IOException ioe) {ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ошибка методов save\\load");
        }
    }

    public void save(OutputStream outputStream) throws Exception {
        //implement this method - реализуйте этот метод
        Properties prop = new Properties();

        for (Map.Entry<String, String> pair : properties.entrySet()) {
            prop.put(pair.getKey(), pair.getValue());
        }
        prop.store(outputStream, "Properties");
    }

    public void load(InputStream inputStream) throws Exception {
        //implement this method - реализуйте этот метод
        Properties prop = new Properties();
        prop.load(inputStream);

        for (Map.Entry<Object, Object> pair : prop.entrySet()) {
            properties.put(String.valueOf(pair.getKey()), String.valueOf(pair.getValue()));
        }
    }

    public static void main(String[] args) {

    }
}
