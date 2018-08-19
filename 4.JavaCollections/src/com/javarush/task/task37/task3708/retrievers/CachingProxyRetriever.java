package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.Storage;

public class CachingProxyRetriever implements Retriever{
    private OriginalRetriever originalRetriever;
    public LRUCache cache = new LRUCache(10);

    // Конструктор класса CachingProxyRetriever должен принимать один параметр типа Storage и инициализировать поле типа OriginalRetriever.
    public CachingProxyRetriever(Storage storage) {
        originalRetriever = new OriginalRetriever(storage);
    }

    // Метод retrieve класса CachingProxyRetriever должен:
    // выполнять поиск подходящего объекта в кеше с помощью метода find.
    // получать объект из хранилища с помощью метода retrieve объекта типа OriginalRetriever и добавлять в кеш, если он не был там найден.
    // не должен вызывать метод retrieve класса OriginalRetriever, если объект был найден в кеше.
    // возвращать объект, которому в хранилище соответствует id, полученный в качестве параметра.

    @Override
    public Object retrieve(long id) {
        Object value = cache.find(id);
        if (value == null) {
            value = originalRetriever.retrieve(id);
            cache.set(id, value);
        }
        return value;
    }
}