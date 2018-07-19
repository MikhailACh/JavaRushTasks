package com.javarush.task.task33.task3310.strategy;

import com.javarush.task.task33.task3310.strategy.StorageStrategy;

import java.util.HashMap;
import java.util.Map;

public class HashMapStorageStrategy implements StorageStrategy {
    private HashMap<Long, String> data = new HashMap<>();

    @Override
    public boolean containsKey(Long key) {
        return data.containsKey(key);
    }

    @Override
    public boolean containsValue(String value) {
        return data.containsValue(value);
    }

    @Override
    public void put(Long key, String value) {
        if (key != null)
            data.put(key, value);
    }

    @Override
    public Long getKey(String value) {
        if (value != null) {
            for (Map.Entry<Long, String> pair : data.entrySet()) {
                if (pair.getValue().equals(value))
                    return pair.getKey();
            }
        }

        return null;
    }

    @Override
    public String getValue(Long key) {
        if (key == null)
            return null;
        return data.get(key);
    }
}
