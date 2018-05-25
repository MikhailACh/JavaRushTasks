package com.javarush.task.task35.task3509;

import java.util.*;


/* 
Collections & Generics
*/
public class Solution {

    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);

        ArrayList arrayList2 = new ArrayList();
        arrayList2.add("a");
        arrayList2.add("b");
        arrayList2.add("c");

        System.out.println(newArrayList(arrayList).getClass().getSimpleName());
        System.out.println(newHashSet(arrayList).getClass().getSimpleName());
        System.out.println(newHashMap(arrayList2, arrayList).getClass().getSimpleName());
    }

    public static<T> ArrayList<T> newArrayList(T... elements) {
        //напишите тут ваш код
        ArrayList<T> al = new ArrayList(Arrays.asList(elements));
        return al;
    }

    public static<T> HashSet<T> newHashSet(T... elements) {
        //напишите тут ваш код
        HashSet<T> hs = new HashSet<>();
        for (T element : elements)
            hs.add(element);
        return hs;
    }

    public static<K, V> HashMap<K, V> newHashMap(List<? extends K> keys, List<? extends V> values) {
        //напишите тут ваш код
        if (keys.size() != values.size())
            throw new IllegalArgumentException();

        HashMap<K, V> hm = new HashMap();
        for (int i = 0; i < keys.size(); i++)
            hm.put(keys.get(i), values.get(i));

        return hm;
    }
}
