package com.javarush.task.task38.task3803;

/* 
Runtime исключения (unchecked exception)
*/

import java.util.ArrayList;
import java.util.Date;

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        Object obj = new Date();
        Integer int1 = (Integer) obj;
    }

    public void methodThrowsNullPointerException() {
        ArrayList<String> aList = null;
        System.out.println(aList.get(1));
    }

    public static void main(String[] args) {
        VeryComplexClass vcc = new VeryComplexClass();
        //vcc.methodThrowsClassCastException();
        vcc.methodThrowsNullPointerException();
    }
}
