package com.javarush.task.task26.task2601;

import java.util.Arrays;
import java.util.Comparator;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {
        Integer[] array = sort(new Integer[]{13, 8, 15, 5, 17, 18});
        for (Integer i : array)
            System.out.println(i);
    }

    public static Integer[] sort(Integer[] array) {
        Arrays.sort(array);
        int median;
        int midPos = array.length / 2;

        if (array.length % 2 == 0)
            median = (array[midPos] + array[midPos - 1]) / 2;
        else
            median = array[midPos];

        /*Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(Math.abs(median - o1), Math.abs(median - o2));
            }
        };*/
        Comparator<Integer> comparator =  (o1 ,o2)->Integer.compare(Math.abs(median-o1), Math.abs(median-o2));

        Arrays.sort(array, comparator);

        return array;
    }
}