package com.javarush.task.task31.task3111;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private String partOfName = null, partOfContent = null;
    private int minSize = -1, maxSize = -1;
    private List<Path> foundFiles = new ArrayList<>();

    // ключи поисковых запрсов
    private boolean isPartOfNameChecking = false;
    private boolean isPartOfContentChecking = false;
    private boolean isMinSizeChecking = false;
    private boolean isMaxSizeChecking = false;

    public List<Path> getFoundFiles() {
        return foundFiles;
    }

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
        this.isPartOfNameChecking = true;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
        this.isPartOfContentChecking = true;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
        this.isMinSizeChecking = true;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
        this.isMaxSizeChecking = true;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        // преобразуем байты файла в массив байтов
        // размер файла: content.length
        byte[] content = Files.readAllBytes(file);

        // логика поиска такова, что если ключ поискового запроса true, то проверяем условие поиска
        // если условие выполняется - идем дальше, к следующему условию
        // иначе - FileVisitResult.CONTINUE, т.е. к следующему файлу
        if (isPartOfNameChecking && !file.getFileName().toString().contains(partOfName))
            return FileVisitResult.CONTINUE;

        if (isPartOfContentChecking && !new String(content).contains(partOfContent))
            return FileVisitResult.CONTINUE;

        if (isMaxSizeChecking && content.length > maxSize)
            return FileVisitResult.CONTINUE;

        if (isMinSizeChecking && content.length < minSize)
            return FileVisitResult.CONTINUE;

        // если добрались сюда, то файл добавляется в массив найденных
        foundFiles.add(file);

        // тем не менее, переходим к следующему файлу
        return FileVisitResult.CONTINUE;
    }
}
