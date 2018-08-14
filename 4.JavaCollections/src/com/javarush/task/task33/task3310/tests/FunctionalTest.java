package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FunctionalTest {
    public void testStorage(Shortener shortener) {
        // 14.4.1. Создавать три строки. Текст 1 и 3 строк должен быть одинаковым.
        String s1 = Helper.generateRandomString();
        String s2 = Helper.generateRandomString();
        String s3 = s1;

        // 14.4.2. Получать и сохранять идентификаторы для всех трех строк с помощью shortener
        long iD1 = shortener.getId(s1);
        long iD2 = shortener.getId(s2);
        long iD3 = shortener.getId(s3);

        // 14.4.3. Проверять, что идентификатор для 2 строки не равен идентификатору для 1 и 3 строк
        Assert.assertNotEquals(iD2, iD1);
        Assert.assertNotEquals(iD2, iD3);

        // 14.4.4. Проверять, что идентификаторы для 1 и 3 строк равны
        Assert.assertEquals(iD1, iD3);

        System.out.println(iD1 + " " + iD2 + " " + iD3);
        System.out.println(s1 + " " + s2 + " " + s3);

        // 14.4.5. Получать три строки по трем идентификаторам с помощью shortener
        // 14.4.6. Проверять, что строки, полученные в предыдущем пункте, эквивалентны оригинальным
        Assert.assertEquals(s1, shortener.getString(iD1));
        Assert.assertEquals(s2, shortener.getString(iD2));
        Assert.assertEquals(s3, shortener.getString(iD3));
    }

    @Test
    public void testHashMapStorageStrategy() {
        Shortener shortener = new Shortener(new HashMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testOurHashMapStorageStrategy() {
        OurHashMapStorageStrategy hashMapStorageStrategy = new OurHashMapStorageStrategy();
        Shortener shortener = new Shortener(hashMapStorageStrategy);
        testStorage(shortener);
    }

    @Test
    public void testFileStorageStrategy() {
        Shortener shortener = new Shortener(new FileStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testHashBiMapStorageStrategy() {
        Shortener shortener = new Shortener(new HashBiMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testDualHashBidiMapStorageStrategy() {
        Shortener shortener = new Shortener(new DualHashBidiMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testOurHashBiMapStorageStrategy() {
        Shortener shortener = new Shortener(new OurHashBiMapStorageStrategy());
        testStorage(shortener);
    }
}