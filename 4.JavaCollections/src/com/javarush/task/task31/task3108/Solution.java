package com.javarush.task.task31.task3108;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/* 
Исследуем Path
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Path path1 = Paths.get("D:/1.txt");
        Path path2 = Paths.get("D:/11/22/55.txt");
        Path resultPath = getDiffBetweenTwoPaths(path1, path2);
        System.out.println(resultPath);   //expected output '../secondDir/third' or '..\secondDir\third'
    }

    public static Path getDiffBetweenTwoPaths(Path path1, Path path2) {
        return path1.relativize(path2);
    }
}
