package com.javarush.task.task36.task3602;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.*;

/* 
Найти класс по описанию
*/
public class Solution {
    public static void main(String[] args) throws NoSuchMethodException{
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        Class[] classes = Collections.class.getDeclaredClasses();

        //System.out.println(ArrayList.class.isInstance(List.class));
        //System.out.println(List.class.isAssignableFrom(LinkedList.class));

        for (Class c : classes) {
            if(Modifier.isPrivate(c.getModifiers()))
                if (Modifier.isStatic(c.getModifiers()))
                    if (List.class.isAssignableFrom(c)) {
                        try {
                            Constructor constructor = c.getDeclaredConstructor();
                            constructor.setAccessible(true);
                            List list = (List) constructor.newInstance();
                            list.get(0);
                        } catch (IndexOutOfBoundsException iobe) {
                            return c;
                        } catch (NoSuchMethodException nsme) {
                            //System.out.println("Constructor of " + c.getSimpleName() + " was not found");
                        } catch (InstantiationException ine) {
                            System.out.println("Can't create an instance");
                        } catch (IllegalAccessException iae) {
                            System.out.println("Access denied");
                        } catch (InvocationTargetException ite) {
                            System.out.println("Can't invocate");
                        }

                    }
        }
        return null;
    }
}