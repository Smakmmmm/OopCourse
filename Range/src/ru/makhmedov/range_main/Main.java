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

        Range range1 = new Range(1, 20);
        Range range2 = new Range(4, 12);
        Range range3 = new Range().getRangeCrossing(range1, range2);
        Range range4 = new Range();

        if (range3 == null) {
            System.out.println("Интервалы не пересекаются");
        } else {
            System.out.println("Пересечение интервалов равно: (" + range3.getFrom() + ", " + range3.getTo() + ")");
        }

        Range[] rangeArray1 = range4.getRangeCombining(range1, range2);

        if (rangeArray1.length == 1) {
            System.out.println("Объединение интервалов равно: (" +
                    rangeArray1[0].getFrom() + ", " + rangeArray1[0].getTo() + ")");
        } else {
            System.out.println("Объединение интервалов равно: (" + rangeArray1[0].getFrom() + ", " +
                    rangeArray1[0].getTo() + ") и (" + rangeArray1[1].getFrom() + ", " + rangeArray1[1].getTo() + ")");
        }

        Range[] rangeArray2 = range4.getRangeDifference(range1, range2);

        if (rangeArray2 == null) {
            System.out.println("Пустое множество");
        } else if (rangeArray2.length == 1) {
            System.out.println("Объединение интервалов равно: (" +
                    rangeArray2[0].getFrom() + ", " + rangeArray2[0].getTo() + ")");
        } else {
            System.out.println("Разность интервалов равно: (" + rangeArray2[0].getFrom() + ", " +
                    rangeArray2[0].getTo() + ") и (" + rangeArray2[1].getFrom() + ", " + rangeArray2[1].getTo() + ")");
        }
    }
}
