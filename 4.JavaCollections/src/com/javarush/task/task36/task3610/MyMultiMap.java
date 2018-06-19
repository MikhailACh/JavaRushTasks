package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        int size = 0;
        for (Map.Entry<K, List<V>> pair : map.entrySet()) {
            size += pair.getValue().size();
        }
        return size;
    }

    @Override
    public V put(K key, V value) {
        List values = map.get(key);
        V old = null;

        if (values == null)
            values = new ArrayList();

        if (map.containsKey(key)) {
            old = (V) values.get(values.size() - 1);
            if (values.size() == repeatCount) {
                values.remove(0);
            }
        }

        values.add(value);
        map.put(key, values);
        return old;
    }

    @Override
    public V remove(Object key) {
        List<V> values = map.get(key);
        if (values == null)
            return null;

        V deleted = values.get(0);
        values.remove(0);

        if (values.size() == 0)
            map.remove(key);

        return deleted;
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        Set<V> values = new HashSet<>();
        for (Map.Entry<K, List<V>> pair : map.entrySet()) {
            for (V value : pair.getValue())
                values.add(value);
        }

        return values;
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        for (Map.Entry<K, List <V>> pair : map.entrySet()) {
            if (pair.getValue().contains(value))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}