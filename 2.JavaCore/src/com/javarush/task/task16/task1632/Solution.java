package com.javarush.task.task16.task1632;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static List<Thread> threads = new ArrayList<>(5);


    static {
        threads.add(new ThreadOne());
        threads.add(new ThreadTwo());
        threads.add(new ThreadThree());
        threads.add(new ThreadFour());
        threads.add(new ThreadFive());
    }


    public static void main(String[] args) {

    }

    public static class ThreadOne extends Thread {
        public void run() {
            while (true) { }
        }
    }

    public static class ThreadTwo extends Thread {
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                interrupt();
                System.out.println("InterruptedException");
            }
        }
    }

    public static class ThreadThree extends Thread {
        public void run() {
            try {
                while (true) {
                    System.out.println("Ура");
                    Thread.sleep(500);
                    }
            } catch (InterruptedException ie) {}
        }
    }

    public static class ThreadFour extends Thread implements Message {
        @Override
        public void showWarning() {
            this.interrupt();
        }

        @Override
        public void run() {
            super.run();
        }
    }

    public static class ThreadFive extends Thread{
        int sum = 0;
        String buffer;

        public void run(){
            try {
                while (true) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                    if ((buffer = br.readLine()).equals("N")) {
                        System.out.println(sum);
                        break;}
                    sum = sum + Integer.parseInt(buffer);}
            } catch (IOException ioe) {
                System.out.println("Неверный тип ввода");
            }
        }
    }
}