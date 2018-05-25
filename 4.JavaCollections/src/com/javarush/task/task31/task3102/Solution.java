package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

/* 
Находим все файлы
*/
public class Solution extends SimpleFileVisitor {

    public static List<String> getFileTree(String root) throws IOException {
        File pathSource = new File(root);

        // список путей файлов
        List<String> result = new ArrayList<>();

        // создаем однонаправленную очередь, куда будем выгружать прочитанные в цикле файлы и папки
        Queue<File> fileTree = new PriorityQueue<>();

        // добавляем в очередь все файлы и папки из директории pathSource
        Collections.addAll(fileTree, pathSource.listFiles());

        // поскольку содержимое очереди извлекаем с удалением, то цикл идет
        // до тех пор, пока очередь fileTree не пустой
        while (!fileTree.isEmpty())
        {
            // текущий файл тот, что извлечен из очереди удалением
            File currentFile = fileTree.remove();
            // а если это не файл, а папка, то и все его содержимое тоже добавляем в очередь fileTree
            if (currentFile.isDirectory()){
                Collections.addAll(fileTree, currentFile.listFiles());
            // иначе добавлеям абсолютный путь файла в списочный массив
            } else {
                result.add(currentFile.getAbsolutePath());
            }
        }
        return result;
    }

    public static void main(String[] args) {
        try{
            getFileTree(args[0]);
        } catch (IOException ioe) {
            System.out.println("I/O Exception!");
        }
    }
}
