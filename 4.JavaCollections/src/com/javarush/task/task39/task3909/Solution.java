package com.javarush.task.task39.task3909;

import static java.lang.Math.abs;

/* 
Одно изменение
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(isOneEditAway("", "")); // true
        System.out.println(isOneEditAway("", "m")); //true
        System.out.println(isOneEditAway("m", "")); //true
        System.out.println("------");
        System.out.println(isOneEditAway("mama", "ramas")); //false
        System.out.println(isOneEditAway("mamas", "rama")); //false
        System.out.println(isOneEditAway("rama", "mama")); //true
        System.out.println(isOneEditAway("mama", "dama")); //true
        System.out.println(isOneEditAway("amms", "amm"));  //false
        System.out.println(isOneEditAway("mama", "ama")); //true
    }

    public static boolean isOneEditAway(String first, String second) {
        if (first.equals(second))
            return true; // по условию задачи требуется именно так

        if (first.equals("") && second.equals(""))
            return true; // по условию задачи требуется именно так

        if (abs(first.length() - second.length()) < 2) {
            String shortest, longest;

            // исключаем влияние пограничных вариантов c выбросом NullPointerException
            if (first.length() > second.length()) {
                shortest = " " + second + " ";
                longest = " " + first + " ";
            } else {
                shortest = " " + first + " ";
                longest = " " + second + " ";
            }

            for (int i = 1; i < longest.length(); i++) {
                if (shortest.charAt(i) != longest.charAt(i)) {
                    String temp = (shortest.length() == longest.length()) ?
                            shortest.substring(0, i) + longest.substring(i) :
                            shortest.substring(0, i) + longest.charAt(i) + shortest.substring(i) ;

                    if (!temp.equals(longest))
                        return false;
                    else
                        return true;
                }
            }
        }
        return false;
    }
}