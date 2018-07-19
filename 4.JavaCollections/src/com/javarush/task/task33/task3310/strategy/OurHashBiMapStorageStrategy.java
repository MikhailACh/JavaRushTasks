package com.javarush.task.task33.task3310.strategy;

import java.util.HashMap;

public class OurHashBiMapStorageStrategy implements StorageStrategy {
    private HashMap<Long, String> k2v = new HashMap<>();
    private HashMap<String, Long> v2k = new HashMap<>();

    @Override
    // должен проверять содержится ли полученный параметр в k2v
    public boolean containsKey(Long key) {
        return k2v.containsKey(key);
    }

    @Override
    // должен проверять содержится ли полученный параметр в v2k
    public boolean containsValue(String value) {
        return v2k.containsKey(value);
    }

    @Override
    // должен добавлять пару (key, value) в k2v и пару (value, key) в v2k
    public void put(Long key, String value) {
        k2v.put(key, value);
        v2k.put(value, key);
    }

    @Override
    // должен возвращать значение полученное из v2k
    public Long getKey(String value) {
        return v2k.get(value);
    }

    @Override
    // должен возвращать значение полученное из k2v
    public String getValue(Long key) {
        return k2v.get(key);
    }
}
