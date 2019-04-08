package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Hippodrome extends Thread {
    static Hippodrome game;
    private List<Horse> horses;

    public Hippodrome() {
        horses = new ArrayList<>();
    }

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            move();
            print();

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void move() {
        for (Horse horse : getHorses())
            horse.move();
    }

    public void print() {
        for (Horse horse : getHorses())
            horse.print();

        for (int i = 0; i < 5; i++)
            System.out.println();
    }

    public Horse getWinner() {
        Collections.sort(getHorses(), new Comparator<Horse>() {
            @Override
            public int compare(Horse o1, Horse o2) {
                return (int) (o2.getDistance() - o1.getDistance());
            }
        });

        return getHorses().get(0);
    }

    public void printWinner() {
        System.out.println("Winner is " + getWinner().getName() + "!");
    }

    public static void main(String[] args) {
        game = new Hippodrome();
        game.getHorses().add(new Horse("Belka", 3, 0));
        game.getHorses().add(new Horse("Strelka", 3, 0));
        game.getHorses().add(new Horse("\uD83D\uDC0E", 3, 0));
        game.run();
        game.printWinner();
    }
}
