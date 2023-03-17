package ru.makhmedov.arrayList_main;

import ru.makhmedov.arrayList.ArrayList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.addAll(Arrays.asList(2, 7, 11, 2, 5, 5));
        System.out.println("Числа: " + Arrays.toString(numbers.toArray()));

        ArrayList<Integer> additionalNumbers = new ArrayList<>(2);
        additionalNumbers.addAll(Arrays.asList(3, 5));
        System.out.println("Добавочные числа: " + Arrays.toString(additionalNumbers.toArray()));

        numbers.addAll(additionalNumbers);
        System.out.println("Результат добавления: " + Arrays.toString(numbers.toArray()));

        ArrayList<Integer> randomNumbersList = new ArrayList<>();
        randomNumbersList.ensureCapacity(3);
        randomNumbersList.add(1);
        randomNumbersList.add(5);
        randomNumbersList.add(1, 11);
        System.out.println("Список случайных чисел: " + Arrays.toString(randomNumbersList.toArray()));

        numbers.removeAll(randomNumbersList);
        System.out.println("Результат removeAll: " + Arrays.toString(numbers.toArray()));

        numbers.trimToSize();
        System.out.println("Размер списка чисел: " + numbers.size());

        if (numbers.isEmpty()) {
            System.out.println("Список пуст!");
        } else {
            System.out.println("Список содержит некоторый набор элементов.");
        }

        System.out.println();
        System.out.println("Проход по списку с помощью итератора:");

        for (int number : numbers) {
            System.out.print(number + " ");
        }
    }
}
