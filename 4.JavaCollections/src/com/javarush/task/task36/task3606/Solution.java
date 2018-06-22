package com.javarush.task.task36.task3606;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/* 
Осваиваем ClassLoader и Reflection
*/
public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "com/javarush/task/task36/task3606/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplse"));
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplf"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {
        File[] files = new File(packageName).listFiles();
        MyClassLoader classLoader = new MyClassLoader();
        for (File f : files) {
            if (f.isFile() && f.getName().endsWith(".class")) {
                Class c = classLoader.loader(f.toPath());
                if (c != null)
                    hiddenClasses.add(c);
            }
        }
    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        for (Class clazz : hiddenClasses) {
            try {
                if (clazz.getSimpleName().toLowerCase().startsWith(key.toLowerCase())) {
                    Constructor[] constructors = clazz.getDeclaredConstructors();
                    for (Constructor constructor : constructors) {
                        if (constructor.getParameterTypes().length == 0) {
                            constructor.setAccessible(true);
                            return (HiddenClass) constructor.newInstance(null);
                        }
                    }
                }
            } catch (InstantiationException ine) {
                System.out.println("Can't create instance!");
            } catch (IllegalAccessException iae) {
                System.out.println("Can't get access to default constructor!");
            } catch (InvocationTargetException ite) {
                System.out.println("Can't invocate default constructor!");
            }
        }
        return null;
    }

    public class MyClassLoader extends ClassLoader {
        public Class<?> loader(Path path) {
            try {
                byte[] b = Files.readAllBytes(path);
                return defineClass(null, b, 0, b.length);
            } catch (IOException ioe) {
                System.out.println("Can't read the Path!");
            }
            return null;
        }
    }
}

