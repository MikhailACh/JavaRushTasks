package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;

import static java.lang.Integer.MAX_VALUE;

/* 
Что внутри папки?
*/
public class Solution extends SimpleFileVisitor<Path> {
    static int fileCount = 0;
    static int dirCount = -1;
    static long size = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String filename = reader.readLine();
        reader.close();

        Solution solution = new Solution();

        Path path = Paths.get(filename);
        if (!Files.isDirectory(path)) {
            System.out.println(path.toAbsolutePath() + " - не папка");
            return;
        }

        EnumSet<FileVisitOption> options = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
        Files.walkFileTree(path, options, 10, solution);

        System.out.println("Всего папок - " + dirCount);
        System.out.println("Всего файлов - " + fileCount);
        System.out.println("Общий размер - " + size);
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        dirCount++;
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
        fileCount++;
        size +=attrs.size();

        return FileVisitResult.CONTINUE;
    }
}
