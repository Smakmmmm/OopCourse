package ru.makhmedov.matrix_main;

import ru.makhmedov.matrix.Matrix;
import ru.makhmedov.vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[][] array1 = {
                {7, 2, 1},
                {11, 2, 6},
                {16, 5, 31}
        };

        Matrix matrix1 = new Matrix(array1);

        System.out.println(matrix1);

        System.out.println("Определитель матрицы 1: " + matrix1.getDeterminant());

        double[][] array2 = {
                {1, 2, 2},
                {3, 1, 1}
        };

        Matrix matrix2 = new Matrix(array2);
        System.out.println("Матрица 2: " + matrix2);
        System.out.println("Количество строк матрицы 2: " + matrix2.getRowsCount());
        System.out.println("Количество столбцов матрицы 2: " + matrix2.getColumnsCount());

        double[][] array3 = {
                {4, 2},
                {3, 1},
                {1, 5}
        };

        Matrix matrix3 = new Matrix(array3);

        Matrix productResult = Matrix.getProduct(matrix3, matrix2);

        System.out.println("Произведение матриц 3 и 2: " + productResult);

        double[][] array4 = {
                {6, 1, 0},
                {10, 1, 5},
                {15, 4, 30}
        };

        Matrix matrix4 = new Matrix(array4);

        Matrix differenceResult = Matrix.getDifference(matrix1, matrix4);
        System.out.println("Разность матриц 1 и 4: " + differenceResult);

        System.out.println("Сумма матриц 1 и 4: " + Matrix.getSum(matrix1, matrix4));

        matrix1.subtract(matrix4);
        System.out.println("Вычитание из матрицы 1 матрицы 4: " + matrix1);

        matrix1.add(matrix4);
        System.out.println("Прибавили к матрице 1 матрицу 4: " + matrix1);

        double[] array5 = {1, 2, 1};
        Vector vector1 = new Vector(array5);

        System.out.println("Умножение матрицы 4 на вектор: " + matrix4.multiplyByVector(vector1));

        matrix4.multiplyByScalar(-1);
        System.out.println("Умножение на скаляр матрицы 4 (на -1): " + matrix4);

        matrix4.transpose();
        System.out.println("Транспонирование матрицы 4: " + matrix4);

        Matrix matrix5 = new Matrix(matrix1);
        matrix5.setRow(2, vector1);
        System.out.println("Установили в матрицу 5 вектор 1: " + matrix5);

        System.out.println();
        System.out.println("Проверка конструктора:");
        Vector[] vectorsArray = {vector1, vector1, vector1};
        Matrix matrix6 = new Matrix(vectorsArray);
        System.out.println(matrix6);

        System.out.println("Тест конструктора с массивом векторов:");
        Vector[] vectors = {
                new Vector(new double[]{1, 2, 3, 4}),
                new Vector(new double[]{1}),
                new Vector(new double[]{1, 2, 3, 4, 5, 6, 7})
        };

        Matrix matrix7 = new Matrix(vectors);
        System.out.println(matrix7);

        double[][] array6 = {
                {6, 1, 0},
                {10, 1, 5},
                {15, 4, 30},
                {2, 3, 4}
        };

        double[] array7 = {1, 2, 1};
        Vector vector2 = new Vector(array7);
        Matrix matrix8 = new Matrix(array6);
        System.out.println("Тест умножения матрицы на вектор: " + matrix8.multiplyByVector(vector2));
        System.out.println("Первая строка матрицы8: " + matrix8.getRow(0));
    }
}
