package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

// класс хранит информация про выбранную валюту
public class CurrencyManipulator {
    private String currencyCode;  // код валюты

    private Map<Integer, Integer> denominations; // <номинал, количество>

    public CurrencyManipulator(String currencyCode) {
        this.denominations = new HashMap<>();
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public boolean hasMoney() {
        return getTotalAmount() != 0;
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return expectedAmount <= getTotalAmount();
    }

    // 2.1. Внимание!!! Метод withdrawAmount должен возвращать минимальное количество банкнот, которыми набирается запрашиваемая сумма.
    // Используйте Жадный алгоритм (use google).
    // Если есть несколько вариантов, то использовать тот, в котором максимальное количество банкнот высшего номинала.
    // Если для суммы 600 результат - три банкноты: 500 + 50 + 50 = 200 + 200 + 200, то выдать первый вариант.
    // 2.2. Мы же не можем одни и те же банкноты выдавать несколько раз, поэтому
    // если мы нашли вариант выдачи денег (п.2.1. успешен), то вычесть все эти банкноты из карты в манипуляторе.
    // 2.3. метод withdrawAmount должен кидать NotEnoughMoneyException, если купюрами невозможно выдать запрашиваемую сумму.
    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException{
        Map<Integer, Integer> amount = new HashMap<>();
        TreeMap<Integer, Integer> atm = new TreeMap<>(denominations);

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
            atm = new TreeMap<>(denominations);

            for (Map.Entry<Integer, Integer> entry : atm.descendingMap().entrySet()) {
                int key = entry.getKey();
                int value = entry.getValue();

                if (expectedAmount % key == 0) {
                    int number = expectedAmount / key;
                    if (number <= value) {
                        sum = key * number;
                        amount.put(key, number);
                        atm.put(key, value - number);
                        break;
                    }
                }
            }
            if (sum == 0)
                throw new NotEnoughMoneyException();
        }

        for (Iterator<Map.Entry<Integer, Integer>> it = atm.entrySet().iterator(); it.hasNext();) {
            if (it.next().getValue() == 0)
                it.remove();
        }

        denominations = new HashMap<>(atm);

        /*System.out.println("В банкомате осталось:");
        for (Map.Entry<Integer, Integer> pair : denominations.entrySet())
            System.out.println(pair.getKey() + "-" + pair.getValue());
        System.out.println("_______________________________________");
        System.out.println("Выдано:");
        for (Map.Entry<Integer, Integer> pair : amount.entrySet())
            System.out.println(pair.getKey() + "-" + pair.getValue());*/

        if (sum != expectedAmount)
            throw new NotEnoughMoneyException();

        return amount;
    }

    public int getTotalAmount() {
        int sum = 0;
        for (Map.Entry<Integer, Integer> pair : denominations.entrySet())
            sum += pair.getKey() * pair.getValue();

        return sum;
    }

    // добавляет введенные номинал и количество банкнот
    public void addAmount(int denomination, int count) {
        if (denominations.containsKey(denomination)) {
            int number = denominations.get(denomination);
            denominations.put(denomination, number + count);
        } else
            denominations.put(denomination, count);
    }
}