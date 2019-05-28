package com.javarush.task.task23.task2312;

public class test {
    private static int height = 20;
    private static int width = 20;

    public static void main(String[] args) {
        print();
    }

    public static void print() {
        int[][] screen = new int[height][width];

        screen[10][10] = 1;
        screen[11][10] = 2;
        screen[12][10] = 2;
        screen[0][5] = 3;

        char[] symbols = {' ', 'X', 'x', '^'};

        for (int i = 0; i < height; i ++) {
            for (int k = 0; k < width; k ++) {
                System.out.print(symbols[screen[k][i]]);
            }
            System.out.println();
        }

        /*for (int i = 0; i < height; i ++) {
            StringBuilder sb = new StringBuilder();

            for (int k = 0; k < width; k ++) {
                switch (screen[i][k]){
                    case (1) :
                        sb.append("X");
                        break;
                    case (2) :
                        sb.append("x");
                        break;
                    case (3) :
                        sb.append("^");
                        break;
                    default:
                        sb.append(" ");
                        break;
                }
            }
            System.out.println(sb.toString());
        }*/

    }
}
