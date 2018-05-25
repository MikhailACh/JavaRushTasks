package com.javarush.task.task31.task3107;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
Null Object Pattern
*/
public class Solution {
    private FileData fileData;
    boolean hidden;
    boolean executable;
    boolean directory;
    boolean writable;

    public Solution(String pathToFile) {
        Path file = Paths.get(pathToFile);
        try {
            hidden = Files.isHidden(file);
            executable = Files.isExecutable(file);
            directory = Files.isDirectory(file);
            writable = Files.isWritable(file);

            this.fileData = new ConcreteFileData(hidden, executable, directory, writable);
        } catch(Exception ioe) {
            this.fileData = new NullFileData(ioe);
        }
    }

    public FileData getFileData() {
        return fileData;
    }
}
