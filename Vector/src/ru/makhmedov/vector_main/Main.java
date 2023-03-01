package ru.makhmedov.vector_main;

import ru.makhmedov.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(new double[]{1, 1, 1, 1});
        System.out.println("Размер вектора1: " + vector1.getSize());

        Vector vector2 = new Vector(6, new double[]{2, 2, 2, 2, 2, 2});
        System.out.println("Длина вектора2: " + vector2.getLength());
        System.out.println();

        Vector vector2Copy = new Vector(vector2);

        System.out.println("Копия вектора2: " + vector2Copy);
        vector2Copy.add(vector1);
        System.out.println("Вектор1: " + vector1);
        System.out.println("Прибавим к копии вектора2 вектор1: " + vector2Copy);
        System.out.println();

        vector2Copy.subtract(vector2);
        System.out.println("Копия вектора2: " + vector2Copy);
        System.out.println("Вектор2: " + vector2);
        System.out.println("Копия вектора2 - вектор2: " + vector2Copy);
        System.out.println();

        vector2Copy.revert();
        System.out.println("Копия вектора2: " + vector2Copy);
        System.out.println("Разворот копии вектора2: " + vector2Copy);
        System.out.println();

        System.out.println("Копия вектора2: " + vector2Copy);
        vector2Copy.multiply(-2.2);
        System.out.println("Умножение копии вектора2 на скаляр: " + vector2Copy);
        System.out.println();

        System.out.println("Вектор1: " + vector1);
        System.out.println("Вектор2: " + vector2);
        Vector vectorsSum = Vector.getSum(vector1, vector2);
        System.out.println("Сумма вектора1 и вектора2: " + vectorsSum);
        System.out.println();

        Vector vector3 = new Vector(3);
        vector3.setComponentByIndex(0, 2.2);
        vector3.setComponentByIndex(2, 14);
        System.out.println("Вектор3:" + vector3);
        System.out.println("Компонента вектора3 под индексом 2: " + vector3.getComponentByIndex(2));
        System.out.println();

        System.out.println("Вектор1: " + vector1);
        System.out.println("Вектор2: " + vector2);
        Vector vectorDifference = Vector.getDifference(vector1, vector2);
        System.out.println("Разность вектора1 и вектора2: " + vectorDifference);

        System.out.println("Скалярное произведение вектора1 на вектор2: " + Vector.getScalarProduct(vector1, vector2));
    }
}
