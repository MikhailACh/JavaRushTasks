package com.javarush.task.task33.task3310.strategy;

public class FileStorageStrategy implements StorageStrategy{

    static final int DEFAULT_INITIAL_CAPACITY = 16;                 // емкость FileBucket[] table по умолчанию
    static final long DEFAULT_BUCKET_SIZE_LIMIT = 10000;            // емкость table[i] по умолчанию
    FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];  // массив файлов-ведер
    int size;                                                       // table.length
    private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;       // установленная начальная емкость table[i]
    long maxBucketSize;                                             // максимально допустимая емкость файла-ведра

    public FileStorageStrategy() {
        for (int i = 0; i < DEFAULT_INITIAL_CAPACITY; i++) {
            table[i] = new FileBucket();
        }
    }

    // getter & setter
    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }
    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }

    // hash calculating
    private int hash(Long k) {
        long h = k;
        h ^= (h >>> 20) ^ (h >>> 12);
        return (int)(h ^ (h >>> 7) ^ (h >>> 4));
    }
    private int indexFor(int hash, int length) {
        return hash % (length - 1);
    }

    // manipulations with Entry
    private Entry getEntry(Long key) {
        int hash = hash(key);
        int index = indexFor(hash, table.length);
        if (table[index] != null) {
            Entry entry = table[index].getEntry();
            while (entry != null) {
                if (entry.getKey().equals(key)) {
                    return entry;
                }
                entry = entry.next;
            }
        }
        return null;
    }

    // create & put new Entry in existing FileBucket file
    private void addEntry(int hash, Long key, String value, int bucketIndex) {
        Entry entry = table[bucketIndex].getEntry();
        table[bucketIndex].putEntry(new Entry(hash, key, value, entry));
        size++;
        if (table[bucketIndex].getFileSize() > bucketSizeLimit) resize(2 * table.length);
    }

    // create new Entry & new FileBucket and put new Entry into new FileBucket regarding [put(Long key, String value)]
    private void createEntry(int hash, Long key, String value, int bucketIndex) {
        table[bucketIndex] = new FileBucket();
        table[bucketIndex].putEntry(new Entry(hash, key, value, null));
        size++;
    }

    // resize & transfer
    private void resize(int newCapacity) {
        FileBucket[] newTable = new FileBucket[newCapacity];
        transfer(newTable);
        // подменяем существующий table новым, удвоенного размера
        table = newTable;
    }

    private void transfer(FileBucket[] newTable) {
        int newCapacity = newTable.length;

        for (FileBucket fb : table) {
            Entry e;
            while ((e = fb.getEntry()) != null){
                // получаем ссылку на следующую запись текущего Entry
                Entry oldNext = e.next;

                // получаем новый индекс для текущей записи в newTable
                int i = indexFor(e.hash, newCapacity);

                // копируем в ссылку на следующий член текущей записи из table
                // запись из newTable с индексом i
                e.next = newTable[i].getEntry();

                // подменяем в newTable запись с индексом i текущей записью из table
                newTable[i].putEntry(e);

                // заменяем текущую запись из table следующей записью из table (т.е. e.next)
                e = oldNext;

                // согласно условию задачи удаляем ставший ненужным файл-ведро
                table[i].remove();
            }
        }
    }


    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value) {
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) continue;
            Entry entry = table[i].getEntry();
            while (entry != null) {
                if (entry.value.equals(value)) return true;
                entry = entry.next;
            }
        }
        return false;
    }

    @Override
    public void put(Long key, String value) {
        int hash = hash(key);
        int index = indexFor(hash, table.length);
        if (table[index] != null) {
            Entry entry = table[index].getEntry();
            while (entry != null) {
                if (entry.getKey().equals(key)) {
                    entry.value = value;
                    return;
                }
                entry = entry.next;
            }
            addEntry(hash, key, value, index);
        }
        else {
            createEntry(hash, key, value, index);
        }
    }

    @Override
    public Long getKey(String value) {
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) continue;
            Entry entry = table[i].getEntry();
            while (entry != null) {
                if (entry.value.equals(value)) return entry.key;
                entry = entry.next;
            }
        }
        return null;
    }

    @Override
    public String getValue(Long key) {
        Entry entry = getEntry(key);
        if (entry != null) return entry.value;
        return null;
    }


}