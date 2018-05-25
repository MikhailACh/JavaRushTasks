package com.javarush.task.task20.task2009;

import java.io.*;

/*
Как сериализовать static?
*/
public class Solution implements Serializable{
    public static class ClassWithStatic implements Serializable{
        public static String staticString = "it's test static string";
        public int i;
        public int j;
    }

    public static void main(String[] args) throws Exception{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:/1.txt"));
        Solution.ClassWithStatic sol = new Solution.ClassWithStatic();
        oos.writeObject(sol);

        oos.flush();
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:/1.txt"));
        Object obj = ois.readObject();
        ois.close();

        Solution.ClassWithStatic sol2 = (Solution.ClassWithStatic) obj;
    }
}
