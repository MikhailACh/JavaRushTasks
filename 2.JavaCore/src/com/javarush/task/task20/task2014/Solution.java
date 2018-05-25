package com.javarush.task.task20.task2014;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/* 
Serializable Solution
*/
public class Solution implements Serializable{
    public static void main(String[] args) {
        System.out.println(new Solution(4));

        Solution savedObject = new Solution(4);
        Solution loadedObject = new Solution(10);
        ObjectOutputStream oos;
        ObjectInputStream ois;

        try{
             oos= new ObjectOutputStream(new FileOutputStream("D:/1.txt"));
             oos.writeObject(savedObject);
             oos.flush();
             oos.close();

             ois = new ObjectInputStream(new FileInputStream("D:/1.txt"));
             loadedObject = (Solution) ois.readObject();
        } catch (FileNotFoundException fnfe) {
             System.out.println("Файл не найден");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Ошибка приведения класса");
        } catch (IOException ioe) {
             System.out.println("Ошибка ввода-вывода");
        }

        System.out.println(savedObject.string.equals(loadedObject.string));
    }

    private transient final String pattern = "dd MMMM yyyy, EEEE";
    private transient Date currentDate;
    private transient int temperature;
    String string;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }
}
