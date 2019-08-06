package com.javarush.task.task25.task2512;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/*
Живем своим умом
*/
public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();

        Stack<Throwable> causes = new Stack<>();
        causes.push(e);
        Throwable current = e.getCause();

        while (current != null) {
            causes.push(current);
            current = current.getCause();
        }

        while (!causes.empty())
            System.out.println(causes.pop());
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        sol.uncaughtException(new Thread(), new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI"))));
    }
}
