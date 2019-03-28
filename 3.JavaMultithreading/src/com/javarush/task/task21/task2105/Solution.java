package com.javarush.task.task21.task2105;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/* 
Исправить ошибку. Сравнение объектов
*/
public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, last);
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Solution))
            return false;
        Solution n = (Solution) o;

        boolean f = false, l = false;

        if ((first != null) && (n.first != null))
            f = first.equals(n.first);

        if (first == null && n.first == null)
            f = true;

        if (last == null && n.last == null)
            l = true;

        if ((last != null) && (n.last != null))
            l = last.equals(n.last);

        return f && l;
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Mickey", "Mouse"));
        System.out.println(s.contains(new Solution("Mickey", "Mouse")));
    }
}