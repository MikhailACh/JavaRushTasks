package com.javarush.task.task33.task3308;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * Created by Оля on 13.02.2018.
 */
@XmlType(name = "shop")
@XmlRootElement
public class Shop {
    public Goods goods;
    public int count;
    public double profit;
    @XmlElement(name = "secretdata")
    public String[] secretData;

    @XmlType(name = "goods")
    @XmlRootElement
    static class Goods {
        List names;
    }
}
