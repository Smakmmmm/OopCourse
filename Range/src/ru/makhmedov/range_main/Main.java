package ru.makhmedov.range_main;

import ru.makhmedov.range.Range;

import java.util.Arrays;
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

        Range range1 = new Range(1, 20);
        Range range2 = new Range(1, 32);

        Range intersectionResult = range1.getIntersection(range2);

        if (intersectionResult == null) {
            System.out.println("Интервалы не пересекаются");
        } else {
            System.out.println("Пересечение интервалов равно: " + intersectionResult);
        }

        Range[] unionResult = range1.getUnion(range2);
        System.out.println("Объединение интервалов равно: " + Arrays.toString(unionResult));

        Range[] differenceResult = range1.getDifference(range2);
        System.out.println("Разность интервалов: " + Arrays.toString(differenceResult));
    }
}
