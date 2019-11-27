package com.javarush.task.task26.task2608;

/* 
Мудрый человек думает раз, прежде чем два раза сказать
*/
public class Solution {
    int var1;
    int var2;
    int var3;
    int var4;

    public Solution(int var1, int var2, int var3, int var4) {
        this.var1 = var1;
        this.var2 = var2;
        this.var3 = var3;
        this.var4 = var4;
    }

    public static void main(String[] args) {
        Solution solution = new Solution(1,2,3,4);

        Runnable task1 = () -> System.out.println(solution.getSumOfVar1AndVar2());
        Runnable task2 = () -> System.out.println(solution.getSumOfVar3AndVar4());
        task1.run();
        task2.run();
    }

    public int getSumOfVar1AndVar2() {
        int result;
        synchronized ((Integer) var1) {
            result = var1 + var2;
        }
        return result;
    }

    public int getSumOfVar3AndVar4() {
        int result;
        synchronized ((Integer) var3) {
            result = var3 + var4;
        }
        return result;
    }
}
