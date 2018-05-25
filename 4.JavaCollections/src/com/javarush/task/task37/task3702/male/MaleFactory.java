package com.javarush.task.task37.task3702.male;

import com.javarush.task.task37.task3702.AbstractFactory;
import com.javarush.task.task37.task3702.Human;

/**
 * Created by Оля on 25.05.2018.
 */
public class MaleFactory implements AbstractFactory {
    public Human getPerson(int age){
        if (age < 13)
            return new KidBoy();
        else if (age > 12 && age < 20)
            return new TeenBoy();
        else return new Man();
    }
}
