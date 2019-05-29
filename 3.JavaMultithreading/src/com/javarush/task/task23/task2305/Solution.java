package com.javarush.task.task23.task2305;

/* 
Inner
*/
public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {
    }

    public static Solution[] getTwoSolutions() {
        Solution[] solutions = new Solution[2];

        Solution sol1 = new Solution();
        Solution.InnerClass in1 = sol1.new InnerClass();
        Solution.InnerClass in2 = sol1.new InnerClass();
        sol1.innerClasses[0] = in1;
        sol1.innerClasses[1] = in2;

        Solution sol2 = new Solution();
        Solution.InnerClass in3 = sol1.new InnerClass();
        Solution.InnerClass in4 = sol1.new InnerClass();
        sol2.innerClasses[0] = in3;
        sol2.innerClasses[1] = in4;

        solutions[0] = sol1;
        solutions[1] = sol2;
        return solutions;
    }

    public static void main(String[] args) {

    }
}