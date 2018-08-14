package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {
    // Он должен возвращать время в миллисекундах необходимое для получения идентификаторов для всех строк из strings. Идентификаторы должны быть записаны в ids
    public long getTimeForGettingIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        Date startDateTime = new Date();

        for (String s : strings) {
            ids.add(shortener.getId(s));
        }

        Date finishDateTime = new Date();

        return finishDateTime.getTime() - startDateTime.getTime();
    }

    // Он должен возвращать время в миллисекундах необходимое для получения строк для всех строк из ids. Строки должны быть записаны в strings
    public long getTimeForGettingStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        Date startDateTime = new Date();

        for (long l : ids) {
            strings.add(shortener.getString(l));
        }

        Date finishDateTime = new Date();

        return finishDateTime.getTime() - startDateTime.getTime();
    }

    @Test
    public void testHashMapStorage() {
        // 15.4.1. Создавать два объекта типа Shortener, один на базе HashMapStorageStrategy, второй на базе HashBiMapStorageStrategy. Назовем их shortener1 и shortener2.
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());

        // 15.4.2. Генерировать с помощью Helper 10000 строк и помещать их в сет со строками, назовем его origStrings.
        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            origStrings.add(Helper.generateRandomString());
        }

        // 15.4.3. Получать время получения идентификаторов для origStrings (вызывать метод getTimeForGettingIds для shortener1, а затем для shortener2)
        Set<Long> ids1 = new HashSet<>();
        Set<Long> ids2 = new HashSet<>();
        long shortener1TimeOfGettingIds = getTimeForGettingIds(shortener1, origStrings, ids1);
        long shortener2TimeOfGettingIds = getTimeForGettingIds(shortener2, origStrings, ids2);

        // 15.4.4. Проверять с помощью junit, что время, полученное в предыдущем пункте для shortener1 больше, чем для shortener2.
        Assert.assertNotEquals(shortener1TimeOfGettingIds, shortener2TimeOfGettingIds);

        // 15.4.5. Получать время получения строк (вызывать метод getTimeForGettingStrings для shortener1 и shortener2)
        long shortener1TimeOfGettingStrings = getTimeForGettingStrings(shortener1, ids1, origStrings);
        long shortener2TimeOfGettingStrings = getTimeForGettingStrings(shortener2, ids2, origStrings);

        // 15.4.6. Проверять с помощью junit, что время, полученное в предыдущем пункте для shortener1 примерно равно времени для shortener2. Используй метод assertEquals(float expected, float actual, float delta). В качестве delta можно использовать 30, этого вполне достаточно для наших экспериментов
        Assert.assertEquals(shortener1TimeOfGettingStrings, shortener2TimeOfGettingStrings, 30);
    }
}