package com.javarush.task.task22.task2208;

import java.util.*;

/* 
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Ivanov");
        map.put("country", "Ukraine");
        map.put("city", "Kiev");
        map.put("age", null);

        System.out.println(getQuery(map));
    }

    public static String getQuery(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();

        if (params == null || params.isEmpty())
            return sb.toString();

        for (Map.Entry<String, String> pair : params.entrySet())
            if (pair.getValue() != null)
                sb.append(String.format(" and %s = '%s'", pair.getKey(), pair.getValue()));

        return sb.toString().replaceFirst("\\s+and\\s+", "");
    }
}