package ru.makhmedov.matrix;

import ru.makhmedov.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] matrixRows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0 || columnsCount <= 0) {
            throw new IllegalArgumentException("The number of rows and columns must be greater than 0. Now the rows count: "
                    + rowsCount + ". Columns count: " + columnsCount + ".");
        }

        matrixRows = fillMatrix(rowsCount, columnsCount);
    }

    public Matrix(Matrix matrix) {
        matrixRows = new Vector[matrix.matrixRows.length];

        for (int i = 0; i < matrixRows.length; i++) {
            matrixRows[i] = new Vector(matrix.matrixRows[i]);
        }
    }

    public Matrix(double[][] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("The dimensions of the passed array must be greater than 0.");
        }

        int maxColumnsCount = 0;

        for (double[] innerArray : array) {
            if (innerArray.length > maxColumnsCount) {
                maxColumnsCount = innerArray.length;
            }
        }

        if (maxColumnsCount == 0) {
            throw new IllegalArgumentException("The dimension of at least one internal array must be greater than 0.");
        }

        matrixRows = new Vector[array.length];

        for (int i = 0; i < matrixRows.length; i++) {
            matrixRows[i] = new Vector(Arrays.copyOf(array[i], maxColumnsCount));
        }
    }

    public Matrix(Vector[] vectorsArray) {
        if (vectorsArray.length == 0) {
            throw new IllegalArgumentException("The dimensions of the passed array must be greater than 0.");
        }

        int maxColumnsCount = 0;

        for (Vector vector : vectorsArray) {
            if (vector.getSize() > maxColumnsCount) {
                maxColumnsCount = vector.getSize();
            }
        }

        matrixRows = new Vector[vectorsArray.length];

        for (int i = 0; i < matrixRows.length; i++) {
            matrixRows[i] = new Vector(maxColumnsCount);

            for (int j = 0; j < maxColumnsCount; j++) {
                matrixRows[i].setComponentByIndex(j, vectorsArray[i].getComponentByIndex(j));
            }
        }
    }

    public int getRowsCount() {
        return matrixRows.length;
    }

    public int getColumnsCount() {
        return matrixRows[0].getSize();
    }

    private static Vector[] fillMatrix(int rowsCount, int columnsCount) {
        Vector[] resultMatrixRows = new Vector[rowsCount];

        for (int i = 0; i < resultMatrixRows.length; i++) {
            resultMatrixRows[i] = new Vector(columnsCount);
        }

        return resultMatrixRows;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= matrixRows.length) {
            throw new IndexOutOfBoundsException("Index out of bounds. (0, " + (matrixRows.length - 1) + "). Now it's: " + index);
        }
    }

    public Vector getRow(int index) {
        checkIndex(index);

        return new Vector(matrixRows[index]);
    }

    public void setRow(int index, Vector vector) {
        checkIndex(index);

        if (vector.getSize() != matrixRows[0].getSize()) {
            throw new IllegalArgumentException("The dimension of the vector must be equal to the number of columns of the matrix. Matrix columns count: "
                    + matrixRows[0].getSize() + ". Vector dimension: " + vector.getSize() + ".");
        }

        matrixRows[index] = new Vector(vector);
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= matrixRows[0].getSize()) {
            throw new IndexOutOfBoundsException("Index out of bounds. (0, " + (matrixRows[0].getSize() - 1) + "). Now it's: " + index);
        }

        Vector columnVector = new Vector(matrixRows.length);

        for (int i = 0; i < matrixRows.length; i++) {
            columnVector.setComponentByIndex(i, matrixRows[i].getComponentByIndex(index));
        }

        return columnVector;
    }

    public void transpose() {
        Vector[] changedMatrixRows = new Vector[matrixRows[0].getSize()];

        for (int i = 0; i < matrixRows[0].getSize(); i++) {
            changedMatrixRows[i] = new Vector(getColumn(i));
        }

        matrixRows = changedMatrixRows;
    }

    public void multiplyByScalar(double scalar) {
        for (Vector vector : matrixRows) {
            vector.multiply(scalar);
        }
    }

    public double getDeterminant() {
        if (matrixRows.length != matrixRows[0].getSize()) {
            throw new UnsupportedOperationException("The determinant can only be found for a square matrix.");
        }

        return getDeterminant(matrixRows);
    }

    private static double getDeterminant(Vector[] matrixRows) {
        int matrixSize = matrixRows.length;

        if (matrixSize == 1) {
            return matrixRows[0].getComponentByIndex(0);
        }

        Vector[] minorMatrix = fillMatrix(matrixSize - 1, matrixSize - 1);
        double determinant = 0;

        for (int i = 0; i < matrixSize; i++) {
            int minorMatrixRowIndex = 0;
            int minorMatrixColumnIndex = 0;

            for (int j = 1; j < matrixSize; j++) {
                for (int k = 0; k < matrixSize; k++) {
                    if (k == i) {
                        continue;
                    }

                    minorMatrix[minorMatrixRowIndex].setComponentByIndex(minorMatrixColumnIndex, matrixRows[j].getComponentByIndex(k));
                    minorMatrixColumnIndex++;

                    if (minorMatrixColumnIndex == matrixSize - 1) {
                        minorMatrixColumnIndex = 0;
                        minorMatrixRowIndex++;
                    }
                }
            }

            determinant += Math.pow(-1, i) * matrixRows[0].getComponentByIndex(i) * getDeterminant(minorMatrix);
        }

        return determinant;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('{');

        for (Vector vector : matrixRows) {
            stringBuilder.append(vector).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append('}');

        return stringBuilder.toString();
    }

    public Vector multiplyByVector(Vector vector) {
        if (matrixRows[0].getSize() != vector.getSize()) {
            throw new IllegalArgumentException("The number of columns in the matrix must match the number of rows in the column vector." +
                    "Columns count in matrix: " + matrixRows[0].getSize() + ". Rows count in vector: " + vector.getSize() + ".");
        }

        Vector product = new Vector(matrixRows[0].getSize());

        int i = 0;

        for (Vector matrixRow : matrixRows) {
            product.setComponentByIndex(i, Vector.getScalarProduct(matrixRow, vector));
            i++;
        }

        return product;
    }

    private static void checkMatricesDimensions(Matrix matrix1, Matrix matrix2) {
        if (matrix1.matrixRows.length != matrix2.matrixRows.length && matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("The dimensions of the matrices must be the same. Matrix1: rows count: " +
                    matrix1.getRowsCount() + ", columns count: " + matrix1.getColumnsCount() + "; Matrix2: rows count: " +
                    matrix2.getRowsCount() + ", columns count: " + matrix2.getColumnsCount() + ".");
        }
    }

    public void add(Matrix matrix) {
        checkMatricesDimensions(this, matrix);

        for (int i = 0; i < matrixRows.length; i++) {
            matrixRows[i].add(matrix.getRow(i));
        }
    }

    public void subtract(Matrix matrix) {
        checkMatricesDimensions(this, matrix);

        for (int i = 0; i < matrixRows.length; i++) {
            matrixRows[i].subtract(matrix.getRow(i));
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        Matrix resultMatrix = new Matrix(matrix1.matrixRows.length, matrix1.getColumnsCount());

        resultMatrix.add(matrix1);
        resultMatrix.add(matrix2);

        return resultMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        Matrix resultMatrix = new Matrix(matrix1.matrixRows.length, matrix1.getColumnsCount());

        resultMatrix.add(matrix1);
        resultMatrix.subtract(matrix2);

        return resultMatrix;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.matrixRows.length) {
            throw new IllegalArgumentException("The number of columns of the first matrix must be equal to the number of rows of the second matrix. Matrix1 columns count: " +
                    matrix1.getColumnsCount() + "; Matrix2 rows count: " + matrix2.matrixRows.length + ".");
        }

        Matrix resultMatrix = new Matrix(matrix1.matrixRows.length, matrix2.getColumnsCount());

        for (int i = 0; i < resultMatrix.matrixRows.length; i++) {
            for (int j = 0; j < resultMatrix.getColumnsCount(); j++) {
                resultMatrix.matrixRows[i].setComponentByIndex(j, Vector.getScalarProduct(matrix1.matrixRows[i], matrix2.getColumn(j)));
            }
        }

        return resultMatrix;
    }
}
