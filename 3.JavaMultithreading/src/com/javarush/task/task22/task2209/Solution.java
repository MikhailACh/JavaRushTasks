package com.javarush.task.task22.task2209;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/*
Составить цепочку слов
*/
public class Solution {
    public static void main(String[] args) {
        Path path;
        String stringFromFile;
        String[] words = null;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            path = Paths.get(br.readLine()); // D:\Java\test.txt
            stringFromFile = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
            words = stringFromFile.split("\\s+");
        } catch (IOException ioe) {
            System.out.println("File not found!");
        }

        StringBuilder result = getLine(words);
        System.out.println(result.toString().trim());
    }

    public static StringBuilder getLine(String... words) {
        StringBuilder result = new StringBuilder();
        if (words == null || words.length == 0) return result;
        if (words.length==1 || words[0].equals("")) return result.append(words[0]);

        ArrayList<String> wordsList = new ArrayList<>();

        wordsList.addAll(Arrays.asList(words));
        while (wordsList.remove("")){
            wordsList.remove("");
        }
        while (isYes(wordsList)) {
            Collections.shuffle(wordsList);
        }
        for (String word: wordsList){
            result.append(word).append(" ");
        }
        result.deleteCharAt(result.length()-1);
        return result;
    }

    public static boolean isYes(ArrayList<String> wordsList) {
        for (int i = 0; i < wordsList.size() - 1; i++) {
            String first = wordsList.get(i).toLowerCase();
            String second = wordsList.get(i + 1).toLowerCase();
            //if (first.charAt(first.length() - 1) != second.charAt(0)) return true;
            if (!first.endsWith(String.valueOf(second.charAt(0)))) return true;
        }
        return false;
    }
}