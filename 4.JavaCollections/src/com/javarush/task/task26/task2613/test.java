package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class test {
    public static void main(String[] args) throws NotEnoughMoneyException {
        Map<Integer, Integer> denominations = new HashMap<>();
        {
            denominations.put(500, 3);
            denominations.put(200, 1);
            denominations.put(100, 2);
            //denominations.put(50, 12);
        }

        int expectedAmount = Integer.parseInt(args[0]);

        Map<Integer, Integer> amount = new HashMap<>();
        TreeMap<Integer, Integer> atm = new TreeMap<>();
        atm.putAll(denominations);

        // жадный алгоритм
        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : atm.descendingMap().entrySet()) {
            int cur = entry.getKey();
            int number = entry.getValue();

            while (number > 0) {
                if (sum + cur > expectedAmount)
                    break;

                sum += cur;
                atm.put(cur, --number);

                if (amount.containsKey(cur)) {
                    int n = amount.get(cur) + 1;
                    amount.put(cur, n);
                } else amount.put(cur, 1);
            }

            if (sum == expectedAmount)
                break;
        }

        // пробуем набрать имеющимися в банкомате купюрами
        if (sum != expectedAmount) {
            sum = 0;
            amount = new HashMap<>();
            atm.putAll(denominations);

            for (Map.Entry<Integer, Integer> entry : atm.descendingMap().entrySet()) {
                int key = entry.getKey();
                int value = entry.getValue();

                int rest = expectedAmount - sum;
                if (rest % key == 0) {
                    int number = expectedAmount / key;
                    if (number <= value) {
                        sum += key * number;
                        amount.put(key, number);
                        atm.put(key, value - number);
                        break;
                    } else {
                        sum += key * value;
                        amount.put(key, value);
                        atm.remove(key);
                    }
                }
            }
            if (sum != expectedAmount)
                throw new NotEnoughMoneyException();
        }

        for (Iterator<Map.Entry<Integer, Integer>> it = atm.entrySet().iterator(); it.hasNext();) {
            if (it.next().getValue() == 0)
                it.remove();
        }

        denominations.putAll(atm);

        System.out.println("В банкомате осталось:");
        for (Map.Entry<Integer, Integer> pair : denominations.entrySet())
            System.out.println(pair.getKey() + "-" + pair.getValue());
        System.out.println("_______________________________________");
        System.out.println("Выдано:");
        for (Map.Entry<Integer, Integer> pair : amount.entrySet())
            System.out.println(pair.getKey() + "-" + pair.getValue());
    }
}