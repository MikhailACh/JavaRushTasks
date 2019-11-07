package com.javarush.task.task26.task2603;

import java.util.Comparator;

/*
Убежденному убеждать других не трудно
*/
public class Solution {
    public static class CustomizedComparator<T> implements Comparator<T>{
        private Comparator<T>[] comparators;

        @SafeVarargs
        public CustomizedComparator(Comparator<T> ... comparators) {
            this.comparators = comparators;
        }

        @Override
        public int compare(T o1, T o2) {
            int diff = 0;
            for (Comparator<T> c : comparators) {
                diff = c.compare(o1, o2);
                if (diff != 0)
                    break;
            }

            return diff;
        }
    }

    public static void main(String[] args) {

    }
}
