package com.javarush.task.task31.task3101;

import java.io.*;
import java.util.*;

/*
Проход по дереву файлов
*/
public class Solution {
    static List<File> filesOver50bytes = new ArrayList<>();

    public static void main(String[] args) {
        File directory = new File(args[0]); // директория, где ищем файлы и папки
        File resultFileAbsolutePath = new File(args[1]); // файл исходный
        File allFilesContent = new File(resultFileAbsolutePath.getParent() + "/allFilesContent.txt"); // файл конечный

        // переименовываем исходный файл
        FileUtils.renameFile(resultFileAbsolutePath, allFilesContent);

        // цикл перебора файлов и папок в директории верхнего уровня
        // если размер больше 50 байт - удаляем, иначе - добавляем в массив
        for (File f : directory.listFiles()) {
            if (f.isFile()) {
                if (f.length() > 50)
                    FileUtils.deleteFile(f);
                else
                    filesOver50bytes.add(f);
            } else if (f.isDirectory()) // если объект директория - передаем его в метод dirAppender
                dirAppender(f);
        }

        // сортировка по именам файлов сосвоим компраратором
        Collections.sort(filesOver50bytes, new Comparator<File>() {
            public int compare(File o1, File o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        // try с ресурсами, закрытие потока не требуется
        try (FileOutputStream writer = new FileOutputStream(allFilesContent)){
            for (File f : filesOver50bytes) {
                FileInputStream reader = new FileInputStream(f);

                int content;
                while ((content = reader.read()) != -1) {
                    writer.write(content);
                }
                writer.write(System.lineSeparator().getBytes()); // вставляем платформонезависимый разделитель строк
                writer.flush();
                reader.close();
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found!");
        } catch (IOException ioe) {
            System.out.println("I/O error!");
        }
    }

    // метод для обработки попадающихся папок
    public static void dirAppender(File dir) {
        for (File f : dir.listFiles()) {
            if (f.isFile()) {
                if (f.length() > 50)
                    FileUtils.deleteFile(f);
                else
                    filesOver50bytes.add(f);
            } else dirAppender(f);
        }
    }
}
