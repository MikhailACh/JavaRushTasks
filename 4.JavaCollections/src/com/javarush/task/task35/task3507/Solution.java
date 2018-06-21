package com.javarush.task.task35.task3507;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/

public class Solution {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) throws IllegalAccessException, InstantiationException {
        Set<Animal> animals = new HashSet<>();

        // массив файлов по заданному адресу
        File[] list = new File(pathToAnimals).listFiles();

        for (File file : list) {
            if (file.isFile() && file.getName().endsWith(".class")) {
                int counter = 0;
                String packageName = Solution.class.getPackage().getName() + ".data"; // имя пакета, содержашего искомые файлы, согласно правилам JAVA
                Class c = new MyClassLoader().load(file.toPath(), packageName);

                Class[] interfaces = c.getInterfaces();
                Constructor[] constructors = c.getConstructors();

                // checking constructors for array parameters length
                for (Constructor constructor : constructors) {
                    if (constructor.getParameterTypes().length == 0)
                        counter++;
                }
                // searching for implemented interfaces by name "Animal"
                for (Class cInterface : interfaces) {
                    if (cInterface.getSimpleName().equals("Animal"))
                        counter++;
                }
                // creating new instances of matched classes
                if (counter == 2) {
                    animals.add((Animal) c.newInstance());
                }
            }
        }
        return animals;
    }

    public static class MyClassLoader extends ClassLoader {
        public Class<?> load(Path path, String packageName) {
            try {
                byte[] buffer = Files.readAllBytes(path); // reading file into the byte buffer by path
                // defining *.class file from byte buffer using package name and filename
                Class c = defineClass(packageName + "." + path.getFileName().toString().replace(".class", ""), buffer, 0, buffer.length);
                return c;
            } catch (IOException ioe) {
                System.out.println("Путь не найден!");
            }
            return null;
        }
    }
}
