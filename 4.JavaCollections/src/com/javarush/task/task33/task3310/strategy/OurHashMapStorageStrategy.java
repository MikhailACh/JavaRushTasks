package com.javarush.task.task33.task3310.strategy;


public class OurHashMapStorageStrategy implements StorageStrategy {
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    Entry[] table = new Entry[DEFAULT_INITIAL_CAPACITY]; // массив-хранилище ссылок на Entry
    int size;
    int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    float loadFactor = DEFAULT_LOAD_FACTOR;

    // реализация интерфейса
    @Override
    public boolean containsKey(Long key) {
        for (Entry e : table) {
            if (e.key == key)
                return true;
        }

        return false;
    }

    @Override
    public boolean containsValue(String value) {
        if (value == null)
            return false;
        for (Entry t : table) {
            for (Entry e = t; e != null; e = e.next)
                if (value.equals(e.value))
                    return true;
        }

        return false;
    }

    @Override
    public void put(Long key, String value) {
        int hash = hash(key);

        int i = indexFor(hash, table.length);

        for (Entry e = table[i]; e != null; e = e.next) {
            Object k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k)))
                e.value = value;
        }

        addEntry(hash, key, value, i);
    }

    @Override
    public Long getKey(String value) {
        if (value == null)
            return 0l;
        for (Entry tableEntry : table) {
            for (Entry e = tableEntry; e != null; e = e.next) {
                if (e.getValue().equals(value))
                    return e.getKey();
            }
        }
        return null;
    }

    /*@Override
    public Long getKey(String value) {

        if (value == null)
            return 0l;
        for (Entry aTable : table) {
            for (Entry e = aTable; e != null; e = e.next)
                if (value.equals(e.value))
                    return aTable.getKey();
        }
        return null;
    }*/

    @Override
    public String getValue(Long key) {
        if (getEntry(key) == null)
            return null;

        return getEntry(key).getValue();
    }

    // методы самого класса
    int hash(Long k) {
        return k.hashCode();
    }

    int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    Entry getEntry(Long key) {
        if (size == 0)
            return null;

        int hash = key.hashCode();
        int bucketIndex = indexFor(hash, table.length);

        for (Entry e = table[bucketIndex]; e != null; e = e.next) {
            Object k;
            if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))));
                return e;
        }

        return null;
    }

    void resize(int newCapacity) {
        if (table.length == threshold)
        {
            threshold = Integer.MAX_VALUE;
            return;
        }

        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable);
        table = newTable;
        threshold = (int)(newCapacity * loadFactor);
    }

    void transfer(Entry[] newTable) {
        int newCapacity = newTable.length;
        for (Entry e : table) {
            if (e != null) {
                // получаем ссылку на следующую запись текущего Entry
                Entry oldNext = e.next;

                // получаем новый индекс для текущей записи в newTable
                int i = indexFor(e.hash, newCapacity);

                // копируем в ссылку на следующий член текущего Entry из table
                // Entry из newTable с индексом i
                e.next = newTable[i];

                // подменяем в newTable запись с индексом i текущей записью из table
                newTable[i] = e;
                // заменяем текущую запись из table следующей записью из table (т.е. e.next)
                e = oldNext;
            }
        }
    }

    void addEntry(int hash, Long key, String value, int bucketIndex) {
        if ((size >= threshold) && (table[bucketIndex] != null)) {
            resize(2 * table.length);

            if (key != null)
                hash = hash(key);
            else hash = 0;

            bucketIndex = indexFor(hash, table.length);
        }

        createEntry(hash, key, value, bucketIndex);
    }

    void createEntry(int hash, Long key, String value, int bucketIndex) {
        Entry existingEntry = table[bucketIndex];
        table[bucketIndex] = new Entry(hash, key, value, existingEntry);
        size++;
    }
}
