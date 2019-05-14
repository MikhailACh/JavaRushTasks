package com.javarush.task.task22.task2207;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) {
        List<String> stringsFromFile = new LinkedList<>();

        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            Path path = Paths.get(br.readLine());
            stringsFromFile = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException ioe) {
            System.out.println("File not found!");
        }

        LinkedList<String> words = new LinkedList<>();
        for (String s : stringsFromFile) {
            String[] temp = s.replace("\uFEFF", "").split("\\s+");
            words.addAll(Arrays.asList(temp));
        }

        for (int i = 0; i < words.size(); i++) {
            StringBuilder sb = new StringBuilder(words.get(i));
            String sbReversed = new StringBuilder(sb).reverse().toString();

            if (words.contains(sbReversed)) {
                String first = sb.toString();

                Pair pair = new Pair(first, sbReversed);
                if (!result.contains(pair))
                    result.add(pair);

                words.removeFirstOccurrence(first);
                words.removeFirstOccurrence(sbReversed);
            }
        }

        for (Pair pair : result) System.out.println(pair);
    }

    public static class Pair {
        String first;
        String second;

        public Pair() {
        }

        public Pair(String first, String second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                        first == null ? second :
                            second == null ? first :
                                first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }
}