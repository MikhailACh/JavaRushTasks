package com.javarush.task.task31.task3109;

import java.io.*;
import java.util.Properties;

/* 
Читаем конфиги
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Properties properties = solution.getProperties("src/com/javarush/task/task31/task3109/properties.xml");
        properties.list(System.out);

        properties = solution.getProperties("src/com/javarush/task/task31/task3109/properties.txt");
        properties.list(System.out);

        properties = solution.getProperties("src/com/javarush/task/task31/task3109/notExists");
        properties.list(System.out);
    }

    public Properties getProperties(String fileName) {
        Properties p = new Properties();
        Reader reader;
        InputStream in;

        try {
            if (fileName.endsWith(".xml")) {
                in = new FileInputStream(fileName);
                p.loadFromXML(in);
            } else if (fileName.endsWith(".txt")) {
                reader = new FileReader(fileName);
                p.load(reader);
            } else {
                in = new FileInputStream(fileName);
                p.load(in);
            }
        } catch (IOException ioe) {return p;}
        return p;
    }
}
