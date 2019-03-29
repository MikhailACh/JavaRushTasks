package com.javarush.task.task21.task2106;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/* 
Ошибка в equals/hashCode
*/
public class Solution {
    private int anInt;
    private String string;
    private double aDouble;
    private Date date;
    private Solution solution;

    public Solution(int anInt, String string, double aDouble, Date date, Solution solution) {
        this.anInt = anInt;
        this.string = string;
        this.aDouble = aDouble;
        this.date = date;
        this.solution = solution;
    }

    // 2. Метод equals должен проверять равен ли переданный объект равен текущему (сравнение через ==).
    //3. Метод equals должен проверять является ли переданный объект объектом класса Solution.
    //4. Метод equals должен проверять значения всех полей у переданного объекта и текущего (учти что некоторые из них могут быть равны null).
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Solution)) return false;
        Solution solution1 = (Solution) o;
        return anInt == solution1.anInt &&
                Double.compare(solution1.aDouble, aDouble) == 0 &&
                Objects.equals(string, solution1.string) &&
                Objects.equals(date, solution1.date) &&
                Objects.equals(solution, solution1.solution);
    }

    @Override
    public int hashCode() {
        return Objects.hash(anInt, string, aDouble, date, solution);
    }

    public static void main(String[] args) {
        Solution share = new Solution(35, "share", 35.0d, new Date(1_200_000), null);
        Solution s1 = new Solution(23, "string", 12.0d, new Date(100_000_000), share);

        Set<Solution> s = new HashSet<>();
        s.add(s1);

        Solution s2 = new Solution(23, "string", 12.0d, new Date(100_000_000), share);
        System.out.println(s.contains(s2));
    }
}