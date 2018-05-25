package com.javarush.task.task37.task3707;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

/**
 * Created by 1914 on 28.03.2018.
 */
public class AmigoSet<E> extends AbstractSet<E> implements Serializable, Cloneable, Set<E> {

    private static final Object PRESENT = new Object();
    private transient HashMap<E,Object> map;

    public AmigoSet() {
        map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        map = new HashMap<E, Object>(Math.max(16, (int) Math.ceil(collection.size()/.75f)));
        addAll(collection);
    }

    public AmigoSet(int capacity) {
        map = new HashMap<>(capacity);
    }

    @Override
    public boolean equals(Object newMap) {
        if (newMap == null) return false;
        if (!(newMap instanceof AmigoSet)) return false;
        if (this.hashCode()!= ((AmigoSet) newMap).hashCode()) return false;

        AmigoSet<E> compare = (AmigoSet) newMap;
        if (this.map.size()!= compare.map.size()) return false;

        for (E e: map.keySet()){
            if (!compare.contains(e)) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return this.map.hashCode()*31+PRESENT.hashCode();
    }

    private void writeObject(ObjectOutputStream oos) throws IOException{
        oos.defaultWriteObject();

        oos.writeInt((int)new HashMapReflectionHelper().callHiddenMethod(map, "capacity"));
        oos.writeFloat((float)new HashMapReflectionHelper().callHiddenMethod(map, "loadFactor"));
        oos.writeInt(map.size());

        for (E e : map.keySet()){
            oos.writeObject(e);
        }
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();

        int capacity = ois.readInt();
        float loadFactor = ois.readFloat();
        int size = ois.readInt();

        map = new HashMap<>(capacity, loadFactor);

        for (int i = 0; i < size; i++){
            E e = (E) ois.readObject();
            map.put(e, PRESENT);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return super.contains(o);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean remove(Object o) {
        return super.remove(o);
    }

    @Override
    public boolean add(E e) {
        return map.put(e, PRESENT) == null;
    }

    @Override
    public Object clone() {
        try {
            AmigoSet copy = (AmigoSet)super.clone();
            copy.map = (HashMap) map.clone();
            return copy;
        } catch (Exception e) {
            throw new InternalError();
        }
    }
}