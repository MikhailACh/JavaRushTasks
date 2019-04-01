package com.javarush.task.task21.task2107;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/* 
Глубокое клонирование карты
*/
public class Solution implements Cloneable{
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Solution)) return false;
        Solution solution = (Solution) o;
        return users.equals(solution.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(users);
    }

    @Override
    protected Solution clone() throws CloneNotSupportedException {
        super.clone();
        Solution solution = new Solution();
        for (Map.Entry<String, User> pair : this.users.entrySet()) {
            User user = (User) pair.getValue().clone();
            solution.users.put(pair.getKey(), user);
        }
        return solution;
    }

    protected Map<String, User> users = new LinkedHashMap();

    public static class User implements Cloneable {
        int age;
        String name;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            super.clone();
            return new User(age, name);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof User)) return false;
            User user = (User) o;
            return age == user.age &&
                    name.equals(user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(age, name);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.users.put("Hubert", new User(172, "Hubert"));
        solution.users.put("Zapp", new User(41, "Zapp"));
        Solution clone = null;
        try {
            clone = solution.clone();
            System.out.println(solution);
            System.out.println(clone);

            System.out.println(solution.users);
            System.out.println(clone.users);

            System.out.println(clone.users.equals(solution.users));

        } catch (CloneNotSupportedException e) {
            e.printStackTrace(System.err);
        }
    }
}