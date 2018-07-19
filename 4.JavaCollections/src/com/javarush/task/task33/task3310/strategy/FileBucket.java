package com.javarush.task.task33.task3310.strategy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {
    Path path;                                                              // путь к файлу

    public FileBucket() {
        try{
            this.path = Files.createTempFile("temp", null);
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException ioe) {
            System.out.println("Can't create file!");
        }
        // преобразуем в файл, стираемый на выходе из программы
        path.toFile().deleteOnExit();
    }

    // он должен возвращать размер файла на который указывает path
    public long getFileSize() {
        try {
            return Files.size(path);
        } catch (IOException ioe) {
            System.out.println("File not found!");
        }
        return 0L;
    }

    // должен сериализовывать переданный entry в файл. Учти, каждый entry может содержать еще один entry.
    public void putEntry(Entry entry) {
        try (ObjectOutputStream ois = new ObjectOutputStream(Files.newOutputStream(path))) {
            ois.writeObject(entry);
        } catch (FileNotFoundException fnfe) {
            System.out.println("Resulting file not found during serialization!");
        } catch (IOException ioe) {
            ioe.getStackTrace();
        }
    }

    // должен забирать entry из файла. Если файл имеет нулевой размер, вернуть null.
    public Entry getEntry() {
        Entry entry = null;

        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path))) {
            if (getFileSize() > 0) {
                entry = (Entry) ois.readObject() ;
            }
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Can't read a String!");
        } catch(IOException ioe) {
            ioe.getStackTrace();
        }
        return entry;
    }

    // удалять файл на который указывает path
    public void remove() {
        try {
            Files.delete(path);
        } catch (IOException ioe) {
            System.out.println("Can't find file for deleting!");
        }
    }
}