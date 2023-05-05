package ru.makhmedov.matrix;

import java.util.Arrays;

public class Matrix {
    private Vector[] matrix;
    private int rowsCount;
    private int columnsCount;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0 || columnsCount <= 0) {
            throw new IllegalArgumentException("Количество строк и столбцов должно быть больше 0. Сейчас количество строк: "
                    + rowsCount + ". Количество столбцов: " + columnsCount + ".");
        }

        this.rowsCount = rowsCount;
        this.columnsCount = columnsCount;

        matrix = new Vector[rowsCount];

        fillMatrix(matrix, columnsCount);
    }

    public Matrix(Matrix matrix) {
        rowsCount = matrix.rowsCount;
        columnsCount = matrix.columnsCount;

        this.matrix = Arrays.copyOf(matrix.matrix, rowsCount);
    }

    public Matrix(double[][] array) {
        if (array.length == 0 || array[0].length == 0) {
            throw new IllegalArgumentException("Размерности передаваемого массива должны быть больше 0.");
        }

        int expectedLength = array[0].length;

        for (double[] innerArray : array) {
            if (expectedLength != innerArray.length) {
                throw new IllegalArgumentException("Размерности всех массивов должны быть одинаковыми.");
            }
        }

        rowsCount = array.length;
        columnsCount = array[0].length;

        matrix = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            matrix[i] = new Vector(array[i]);
        }
    }

    public Matrix(Vector[] vectorsArray) {
        if (vectorsArray.length == 0 || vectorsArray[0].getSize() == 0) {
            throw new IllegalArgumentException("Размерности передаваемого массива должны быть больше 0.");
        }

        if (vectorsArray.length > 1) {
            checkVectorsDimensions(vectorsArray);
        }

        rowsCount = vectorsArray.length;
        columnsCount = vectorsArray[0].getSize();

        this.matrix = Arrays.copyOf(vectorsArray, rowsCount);
    }

    private void checkVectorsDimensions(Vector[] vectorsArray) {
        int expectedLength = vectorsArray[0].getSize();

        for (Vector vector : vectorsArray) {
            if (expectedLength != vector.getSize()) {
                throw new IllegalArgumentException("Размерности всех векторов должны быть одинаковыми.");
            }
        }
    }

    private void fillMatrix(Vector[] matrix, int vectorDimension) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = new Vector(vectorDimension);
        }
    }

    public int getRowsCount() {
        return rowsCount;
    }

    public int getColumnsCount() {
        return columnsCount;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= rowsCount) {
            throw new IndexOutOfBoundsException("Индекс за пределами диапазона. (0, " + (rowsCount - 1) + "). Текущее значение: " + index);
        }
    }

    public Vector getRowVector(int index) {
        checkIndex(index);

        return matrix[index];
    }

    public void setVector(int index, Vector vector) {
        checkIndex(index);

        if (vector.getSize() > columnsCount) {
            throw new IllegalArgumentException("Размерность вектора не может быть больше размерности матрицы. Количество столбцов матрицы: "
                    + columnsCount + ". Размерность вектора: " + vector.getSize() + ".");
        }

        matrix[index] = new Vector(vector, columnsCount);
    }

    public Vector getColumnVector(int index) {
        if (index < 0 || index >= columnsCount) {
            throw new IndexOutOfBoundsException("Индекс за пределами диапазона: (0, " + (columnsCount - 1) + "). Текущее значение: " + index + ".");
        }

        Vector columnVector = new Vector(rowsCount);

        for (int i = 0; i < rowsCount; i++) {
            columnVector.setComponentByIndex(i, matrix[i].getComponentByIndex(index));
        }

        return columnVector;
    }

    public void transpose() {
        Vector[] transposedMatrix = new Vector[columnsCount];

        for (int i = 0; i < columnsCount; i++) {
            transposedMatrix[i] = new Vector(getColumnVector(i), rowsCount);
        }

        matrix = transposedMatrix;

        rowsCount = transposedMatrix.length;
        columnsCount = transposedMatrix[0].getSize();
    }

    public void multiplyByScalar(double scalar) {
        for (Vector vector : matrix) {
            vector.multiply(scalar);
        }
    }

    public double getDeterminant() {
        if (rowsCount != columnsCount) {
            throw new IllegalArgumentException("Определитель можно найти только для квадратной матрицы.");
        }

        return getDeterminant(matrix);
    }

    private double getDeterminant(Vector[] matrix) {
        int matrixSize = matrix.length;

        if (matrixSize == 1) {
            return matrix[0].getComponentByIndex(0);
        }

        Vector[] minorMatrix = new Vector[matrixSize - 1];
        fillMatrix(minorMatrix, matrixSize - 1);

        int result = 0;

        for (int i = 0; i < matrixSize; i++) {
            int minorMatrixRowIndex = 0;
            int minorMatrixColumnIndex = 0;

            for (int j = 1; j < matrixSize; j++) {
                for (int k = 0; k < matrixSize; k++) {
                    if (k == i) {
                        continue;
                    }

                    minorMatrix[minorMatrixRowIndex].setComponentByIndex(minorMatrixColumnIndex, matrix[j].getComponentByIndex(k));
                    minorMatrixColumnIndex++;

                    if (minorMatrixColumnIndex == matrixSize - 1) {
                        minorMatrixColumnIndex = 0;
                        minorMatrixRowIndex++;
                    }
                }
            }

            result += Math.pow(-1, i) * matrix[0].getComponentByIndex(i) * getDeterminant(minorMatrix);
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('{');

        for (Vector vector : matrix) {
            stringBuilder.append(vector.toString()).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append('}');

        return stringBuilder.toString();
    }

    public void multiplyByVector(Vector vector) {
        if (columnsCount != vector.getSize()) {
            throw new IllegalArgumentException("Количество столбцов в матрице должно совпадать с количеством строк в векторе-столбце." +
                    "Количество столбцов в матрице: " + columnsCount + ". Количество строк в векторе: " + vector.getSize() + ".");
        }

        Vector[] product = new Vector[columnsCount];

        int i = 0;

        for (Vector matrixVector : matrix) {
            product[i] = new Vector(1);
            product[i].setComponentByIndex(0, Vector.getScalarProduct(matrixVector, vector));
            i++;
        }

        matrix = product;
        rowsCount = columnsCount;
        columnsCount = 1;
    }

    private static void checkMatricesDimensions(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rowsCount != matrix2.rowsCount && matrix1.columnsCount != matrix2.columnsCount) {
            throw new IllegalArgumentException("Размерности матриц должны быть одинаковыми.");
        }
    }

    public void add(Matrix matrix) {
        checkMatricesDimensions(this, matrix);

        int i = 0;

        for (Vector vector : this.matrix) {
            vector.add(matrix.getRowVector(i));
            i++;
        }
    }

    public void subtract(Matrix matrix) {
        checkMatricesDimensions(this, matrix);

        int i = 0;

        for (Vector vector : this.matrix) {
            vector.subtract(matrix.getRowVector(i));
            i++;
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        checkMatricesDimensions(matrix1, matrix2);

        Matrix resultMatrix = new Matrix(matrix1.rowsCount, matrix1.columnsCount);

        for (int i = 0; i < matrix1.rowsCount; i++) {
            resultMatrix.matrix[i] = Vector.getSum(matrix1.matrix[i], matrix2.matrix[i]);
        }

        return resultMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        checkMatricesDimensions(matrix1, matrix2);

        Matrix resultMatrix = new Matrix(matrix1.rowsCount, matrix1.columnsCount);

        for (int i = 0; i < matrix1.rowsCount; i++) {
            resultMatrix.matrix[i] = Vector.getDifference(matrix1.matrix[i], matrix2.matrix[i]);
        }

        return resultMatrix;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.columnsCount != matrix2.rowsCount) {
            throw new IllegalArgumentException("Количество столбцов первой матрицы должно быть равно количеству строк второй матрицы.");
        }

        Matrix resultMatrix = new Matrix(matrix1.rowsCount, matrix2.columnsCount);

        int matrix1RowsIndex = 0;

        for(Vector vector : resultMatrix.matrix) {
            for (int i = 0; i < resultMatrix.columnsCount; i++) {
                vector.setComponentByIndex(i, Vector.getScalarProduct(matrix1.matrix[matrix1RowsIndex], matrix2.getColumnVector(i)));
            }

            matrix1RowsIndex++;
        }

        return resultMatrix;
    }
}
