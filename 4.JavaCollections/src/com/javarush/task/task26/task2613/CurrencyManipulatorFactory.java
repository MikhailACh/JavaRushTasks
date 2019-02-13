package com.javarush.task.task26.task2613;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

// фабрика манипуляторов валютами
public class CurrencyManipulatorFactory {
    private static Map<String, CurrencyManipulator> currency = new HashMap<>();


    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        if (!currency.containsKey(currencyCode))
            currency.put(currencyCode, new CurrencyManipulator(currencyCode));

        return currency.get(currencyCode);
    }

    private CurrencyManipulatorFactory() {
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators() {
        return currency.values();
    }
}
