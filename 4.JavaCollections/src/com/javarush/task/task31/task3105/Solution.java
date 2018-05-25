package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String filename = args[0]; // полный путь файла для архива
        String zipArchive = args[1]; // полный путь zip-архива
        String shortFilename = filename.substring(filename.lastIndexOf("\\") + 1); // извлекаем имя файла из его пути

        // поток чтения zip-архива
        ZipInputStream zis = new ZipInputStream(new FileInputStream(zipArchive));
        // карта хранения прочтенных файлов имя-массив байтов
        HashMap<String, ByteArrayOutputStream> entries = new HashMap<>();

        // объявляем zip-файл
        ZipEntry ze;
        // пока есть файлы (Entry) во входном потоке чтения zip-файлов
        while ((ze = zis.getNextEntry()) != null) {
            // выходной поток байтов, который выводит читает байты в оперативную память
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            // байтовый массив (буфер) размером 1024 байта, куда читаем содержимое записи (zip-файла) частями
            byte[] buffer = new byte[1024];

            int count;
            // пока прочитанное количество байт не равно 0
            while ((count = zis.read(buffer)) > -1)
                // добаляем содержимое в обычный байтовый массив
                bout.write (buffer, 0, count);
            // вычитываем содержимое архива в карту типа имя-содержимое записи в вде массива байтов
            entries.put(ze.getName(), bout);
            // обязательно закрываем запись (файл)
            zis.closeEntry();
        }
        zis.close();

        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipArchive));

        for (Map.Entry<String,ByteArrayOutputStream> pair : entries.entrySet()) {
            // если имена файла и записи в архиве совпадают - следующий шаг цикла
            if (pair.getKey().equals(shortFilename))
                continue;
            // создаем в исходящем потоке новую запись
            zos.putNextEntry(new ZipEntry(pair.getKey()));
            // и пишем запись (файл) из карты в поток в виде массива байтов
            zos.write(pair.getValue().toByteArray());
        }

        // создаем запись для нашего файла (файл будет находиться в архиве в папке new\)
        ZipEntry zentry = new ZipEntry("new\\" + shortFilename);
        // и сам объект для файла
        File file = new File(filename);
        zos.putNextEntry(zentry);
        // копируем запись (файл) в существующий поток записи
        Files.copy(file.toPath(), zos);
        // закрываем поток с одновременной записью содержимого в архив
        zos.close();
    }
}