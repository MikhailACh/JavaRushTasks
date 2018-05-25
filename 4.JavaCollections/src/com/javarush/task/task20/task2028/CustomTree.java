package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable{
    Entry<String> root = new Entry<>("0");

    public static void main(String[] args) {
        List<String> list = new CustomTree();
        for (int i = 1; i < 16; i++) {
            list.add(String.valueOf(i));
        }

        System.out.println("Expected 3, actual is " + ((CustomTree) list).getParent("8"));
        //list.remove("5");
        System.out.println("Expected 7, actual is " + ((CustomTree) list).getParent("15"));
        //System.out.println("size:" + list.size() + "; removing 5: " + list.remove("5") + "; size after:" + list.size());
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    // переписываем методы согласно заданию
    public String getParent(String s) {
        if (s == null) return null;

        Queue<Entry<String>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Entry<String> current = queue.poll();

            if (current.leftChild != null) {
                if (current.leftChild.elementName.equals(s)) {
                    return current.leftChild.parent.elementName;
                } else queue.offer(current.leftChild);
            } else return null;

            if (current.rightChild != null) {
                if (current.rightChild.elementName.equals(s)) {
                    return current.rightChild.parent.elementName;
                } else queue.offer(current.rightChild);
            } else return null;
        }
        return null;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) return false;

        String target = (String) o;
        Entry<String> rootElement;

        Queue<Entry<String>> removing = new LinkedList<>();
        removing.offer(root);

        while (!removing.isEmpty()) {
            rootElement = removing.poll();

            if (!rootElement.leftChild.elementName.equals(target)) {
                removing.offer(rootElement.leftChild);
            } else if (rootElement.leftChild.elementName.equals(target)) {
                System.out.println("Удален элемент " + rootElement.leftChild.elementName);
                rootElement.leftChild = null;
                return true;
            }

            if (!rootElement.rightChild.elementName.equals(target)) {
                removing.offer(rootElement.rightChild);
            } else if (rootElement.rightChild.elementName.equals(target)) {
                System.out.println("Удален элемент " + rootElement.rightChild.elementName);
                rootElement.rightChild = null;
                return true;
            }
        }
        return true;
    }

    @Override
    public boolean add(String s) {
        if (s == null)
            return false;

        Queue<Entry<String>> adding = new LinkedList<>();
        adding.offer(root);

        while (!adding.isEmpty()) {
            Entry<String> current = adding.poll();
            current.checkChildren();

            if (current.isAvailableToAddChildren()) {
                if (current.availableToAddLeftChildren) {
                    current.leftChild = new Entry<>(s);
                    current.leftChild.parent = current;

                    return true;
            }
                else if (current.availableToAddRightChildren) {
                    current.rightChild = new Entry<>(s);
                    current.rightChild.parent = current;

                    return true;
                }
            } else {
                if (current.leftChild != null) {
                    adding.offer(current.leftChild);
                }
                if (current.rightChild != null) {
                    adding.offer(current.rightChild);
                }
            }
        }
        return false;
    }

    @Override
    public int size() {
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.offer(root);
        int size = 0;

        while (!queue.isEmpty()) {
            Entry<String> current = queue.poll();

            if (current.leftChild != null) {
                size++;
                queue.offer(current.leftChild);
            }
            if (current.rightChild != null) {
                size++;
                queue.offer(current.rightChild);
            }
        }
        return size;
    }

    // класс, описывающий узел
    static class Entry<T> implements Serializable {
        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry (String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        void checkChildren() {
            if (leftChild != null)
                availableToAddLeftChildren = false;
            if (rightChild != null)
                availableToAddRightChildren = false;
        }

        boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren || availableToAddRightChildren;
        }
    }
}
