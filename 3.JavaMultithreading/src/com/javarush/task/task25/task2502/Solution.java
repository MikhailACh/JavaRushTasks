package com.javarush.task.task25.task2502;

import java.util.*;

/* 
Машину на СТО не повезем!
*/
public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        // Инициализируй поле wheels используя данные из loadWheelNamesFromDB.
        // Выкинь исключение в случае некорректных данных.
        public Car() {
            wheels = new ArrayList<>();
            if (loadWheelNamesFromDB().length == 4) {
                wheels.add(0, Wheel.valueOf(loadWheelNamesFromDB()[0]));
                wheels.add(1, Wheel.valueOf(loadWheelNamesFromDB()[1]));
                wheels.add(2, Wheel.valueOf(loadWheelNamesFromDB()[2]));
                wheels.add(3, Wheel.valueOf(loadWheelNamesFromDB()[3]));
            } else
                throw new IllegalArgumentException();
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }

    public static void main(String[] args) {
    }
}
