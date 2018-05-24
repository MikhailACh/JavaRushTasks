package com.javarush.task.task33.task3303;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/* 
Десериализация JSON объекта
*/
public class Solution {
    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File fr = new File(fileName);

        return mapper.readValue(fr, clazz);
    }

    public static void main(String[] args) throws IOException{
        String JSON = args[0];
        Class fromFile = null;
        try {
            fromFile = Class.forName(args[1]);
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Нет такого класса!");}

        convertFromJsonToNormal(JSON, fromFile);

    }
}
