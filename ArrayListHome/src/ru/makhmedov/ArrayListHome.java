package ru.makhmedov;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;

public class ArrayListHome {
    public static void main(String[] args) {
        System.out.println("Задача 1");

        try (BufferedReader reader = new BufferedReader(new FileReader("ArrayListHome\\src\\ru\\makhmedov\\lines.txt"))) {
            ArrayList<String> fileStrings = new ArrayList<>();

            String string;

            while ((string = reader.readLine()) != null) {
                fileStrings.add(string);
            }

            System.out.println("Список строк из файла: " + fileStrings);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println();
        System.out.println("Задача 2");

        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        System.out.println("Список до удаления четных чисел: " + numbers);
        numbers.removeIf(x -> x % 2 == 0);
        System.out.println("Список после удаления четных чисел: " + numbers);

        System.out.println();
        System.out.println("Задача 3");

        ArrayList<Integer> repeatingNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 1, 2, 3, 1, 2, 5, 4));
        ArrayList<Integer> nonRepeatingNumbers = new ArrayList<>(new LinkedHashSet<>(repeatingNumbers));
        System.out.println("Список с повторяющимися числами: " + repeatingNumbers);
        System.out.println("Список без повторяющихся чисел: " + nonRepeatingNumbers);
    }
}
