package ru.makhmedov.array_list_home;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListHome {
    public static void main(String[] args) {
        System.out.println("Задача 1");

        try (BufferedReader reader = new BufferedReader(new FileReader("ArrayListHome/src/ru/makhmedov/array_list_home/lines.txt"))) {
            ArrayList<String> fileLines = new ArrayList<>();

            String line;

            while ((line = reader.readLine()) != null) {
                fileLines.add(line);
            }

            System.out.println("Список строк из файла: " + fileLines);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден!");
        } catch (IOException e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }

        System.out.println();
        System.out.println("Задача 2");

        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        System.out.println("Список до удаления четных чисел: " + numbers);

        for (int i = numbers.size() - 1; i >= 0; i--) {
            if (numbers.get(i) % 2 == 0) {
                numbers.remove(i);
            }
        }

        System.out.println("Список после удаления четных чисел: " + numbers);

        System.out.println();
        System.out.println("Задача 3");

        ArrayList<Integer> repeatingNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 1, 2, 3, 1, 2, 5, 4));
        System.out.println("Список с повторяющимися числами: " + repeatingNumbers);

        ArrayList<Integer> nonRepeatingNumbers = new ArrayList<>(repeatingNumbers.size());

        for (Integer number: repeatingNumbers) {
            if (!nonRepeatingNumbers.contains(number)) {
                nonRepeatingNumbers.add(number);
            }
        }

        System.out.println("Список без повторяющихся чисел: " + nonRepeatingNumbers);
    }
}
