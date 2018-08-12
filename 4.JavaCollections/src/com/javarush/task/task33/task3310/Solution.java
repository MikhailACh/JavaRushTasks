package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        StorageStrategy strategyOne = new HashMapStorageStrategy();
        testStrategy(strategyOne,10000);
        StorageStrategy strategyTwo = new OurHashMapStorageStrategy();
        testStrategy(strategyTwo, 10000);
        StorageStrategy strategyThree = new FileStorageStrategy();
        testStrategy(strategyThree, 100);
        StorageStrategy strategyFour = new OurHashBiMapStorageStrategy();
        testStrategy(strategyFour, 10000);
        StorageStrategy strategyFive = new HashBiMapStorageStrategy();
        testStrategy(strategyFive, 10000);
        StorageStrategy strategySix = new DualHashBidiMapStorageStrategy();
        testStrategy(strategySix, 10000);
    }

    // 6.2.1. Этот метод должен для переданного множества строк возвращать множество идентификаторов. Идентификатор для каждой отдельной строки нужно получить, используя shortener.
    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> getIDs = new HashSet<>();
        for (String s : strings) {
            getIDs.add(shortener.getId(s));
        }
        return getIDs;
    }

    // 6.2.2. Метод будет возвращать множество строк, которое соответствует переданному множеству идентификаторов.
    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> getStrings = new HashSet<>();
        for (long key : keys) {
            getStrings.add(shortener.getString(key));
        }
        return getStrings;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        //6.2.3.1. Выводить имя класса стратегии. Имя не должно включать имя пакета.
        System.out.println(strategy.getClass().getSimpleName());


        //6.2.3.2. Генерировать тестовое множество строк
        Set<String> strings = new HashSet<>();
        Long[] elements = new Long[(int) elementsNumber];

        for (int i = 0; i < elementsNumber; i++)
            strings.add(Helper.generateRandomString());

        //6.2.3.3. Создавать объект типа Shortener, используя переданную стратегию.
        Shortener shortener = new Shortener(strategy);

        //6.2.3.4. Замерять и выводить время необходимое для отработки метода getIds для заданной стратегии
        Date startDateTime = new Date();
        Set<Long> ids = getIds(shortener, strings);
        Date finishDateTime = new Date();

        long deltaTime = finishDateTime.getTime() - startDateTime.getTime();
        Helper.printMessage(Long.toString(deltaTime));

        //6.2.3.5. Замерять и выводить время необходимое для отработки метода getStrings для заданной стратегии
        startDateTime = new Date();
        Set<String> strs = getStrings(shortener, ids);
        finishDateTime = new Date();

        deltaTime = finishDateTime.getTime() - startDateTime.getTime();
        Helper.printMessage(Long.toString(deltaTime));


        //6.2.3.6. Сравнивать одинаковое ли содержимое множества строк, которое было сгенерировано и множества, которое было возвращено методом getStrings.
        if (strings.equals(strs))
            Helper.printMessage("Тест пройден.");
        else
            Helper.printMessage("Тест не пройден.");
    }
}