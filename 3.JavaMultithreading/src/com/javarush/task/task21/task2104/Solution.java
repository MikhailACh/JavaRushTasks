package com.javarush.task.task21.task2104;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/* 
Equals and HashCode
*/
public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Donald", "Duck"));
        System.out.println(s.contains(new Solution("Donald", "Duck")));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Solution))
            return false;

        Solution solution = (Solution) o;

        boolean f = false, l = false;

        if ((first != null) && (solution.first != null))
            f = first.equals(solution.first);

        if (first == null && solution.first == null)
            f = true;

        if (last == null && solution.last == null)
            l = true;

        if ((last != null) && (solution.last != null))
            l = last.equals(solution.last);

        return f && l;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, last);
    }
}