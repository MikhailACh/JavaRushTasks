package com.javarush.task.task37.task3702;

import com.javarush.task.task37.task3702.female.FemaleFactory;
import com.javarush.task.task37.task3702.male.MaleFactory;
import static com.javarush.task.task37.task3702.FactoryProducer.HumanFactoryType.MALE;

/**
 * Created by Оля on 25.05.2018.
 */
public class FactoryProducer {
    public static enum HumanFactoryType {MALE, FEMALE};

    public static AbstractFactory getFactory(HumanFactoryType hft){
        return hft == MALE ? new MaleFactory() : new FemaleFactory();
    }
}
