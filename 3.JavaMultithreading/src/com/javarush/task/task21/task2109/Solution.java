package com.javarush.task.task21.task2109;

import java.util.Objects;

/*
Запретить клонирование
*/
public class Solution {
    public static class A implements Cloneable {
        private int i;
        private int j;

        public A(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }
    }

    public static class B extends A {
        private String name;

        public B(int i, int j, String name) {
            super(i, j);
            this.name = name;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            throw new CloneNotSupportedException();
        }

        public String getName() {
            return name;
        }
    }

    public static class C extends B implements Cloneable {
        public C(int i, int j, String name) {
            super(i, j, name);
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return new C(getI(), getJ(), getName());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof C)) return false;
            C c = (C) o;
            return getI() == c.getI() &&
                    getJ() == c.getJ();
        }

        @Override
        public int hashCode() {
            return Objects.hash(getI(), getJ());
        }
    }

    public static void main(String[] args) {
        C c1 = new C(1, 2, "original");
        C c2 = null;
        try {
            c2 = (C) c1.clone();
        } catch (CloneNotSupportedException cnse) {
            cnse.printStackTrace();
        }

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c1.equals(c2));
    }
}
