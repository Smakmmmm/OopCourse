package ru.makhmedov.array_list_main;

import ru.makhmedov.array_list.ArrayList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.addAll(Arrays.asList(2, 7, 11, 2, 5, 5));
        numbers.addAll(3, Arrays.asList(0,0,0));
        System.out.println("Числа: " + numbers);

        ArrayList<Integer> additionalNumbers = new ArrayList<>(2);
        additionalNumbers.addAll(Arrays.asList(3, 5));
        System.out.println("Добавочные числа: " + additionalNumbers);

        numbers.addAll(additionalNumbers);
        System.out.println("Результат добавления: " + numbers);

        ArrayList<Integer> randomNumbersList = new ArrayList<>();
        randomNumbersList.ensureCapacity(3);
        randomNumbersList.add(1);
        randomNumbersList.add(5);
        randomNumbersList.add(1, 11);
        System.out.println("Список случайных чисел: " + randomNumbersList);

        numbers.removeAll(randomNumbersList);
        System.out.println("Результат removeAll: " + numbers);

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
