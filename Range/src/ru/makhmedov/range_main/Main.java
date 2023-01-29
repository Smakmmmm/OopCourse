package ru.makhmedov.range_main;

import ru.makhmedov.range.Range;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Range range = new Range(10, 100);

        System.out.println("Длина диапазона равна: " + range.getLength());

        System.out.println("Введите число:");
        double number = scanner.nextDouble();

        if (range.isInside(number)) {
            System.out.println("Число лежит в заданном диапазоне.");
        } else {
            System.out.println("Число лежит вне диапазона.");
        }

        System.out.println("Задайте новый диапазон. Начало диапазона:");
        range.setFrom(scanner.nextDouble());

        System.out.println("Введите конец диапазона:");
        range.setTo(scanner.nextDouble());

        System.out.println("Теперь начало диапазона: " + range.getFrom() + " и конец: " + range.getTo());
        System.out.println("Длина заданного Вами диапазона равна: " + range.getLength());

        System.out.println("Введите новое число:");
        number = scanner.nextDouble();

        if (range.isInside(number)) {
            System.out.println("Число лежит в заданном диапазоне.");
        } else {
            System.out.println("Число лежит вне диапазона.");
        }
    }
}
