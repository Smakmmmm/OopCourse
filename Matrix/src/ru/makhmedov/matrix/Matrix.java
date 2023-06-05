package ru.makhmedov.matrix;

import ru.makhmedov.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0 || columnsCount <= 0) {
            throw new IllegalArgumentException("The rows and columns counts must be greater than 0. Now the rows count: "
                    + rowsCount + ". Columns count: " + columnsCount + ".");
        }

        rows = getVectorsArray(rowsCount, columnsCount);
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.rows.length];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(matrix.rows[i]);
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

        rows = new Vector[array.length];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(Arrays.copyOf(array[i], maxColumnsCount));
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

        rows = new Vector[vectorsArray.length];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(maxColumnsCount);

            for (int j = 0; j < vectorsArray[i].getSize(); j++) {
                rows[i].setComponentByIndex(j, vectorsArray[i].getComponentByIndex(j));
            }
        }
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    private static Vector[] getVectorsArray(int rowsCount, int columnsCount) {
        Vector[] resultMatrixRows = new Vector[rowsCount];

        for (int i = 0; i < resultMatrixRows.length; i++) {
            resultMatrixRows[i] = new Vector(columnsCount);
        }

        return resultMatrixRows;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("Index out of bounds. (0, " + (rows.length - 1) + "). Now it's: " + index);
        }
    }

    public Vector getRow(int index) {
        checkIndex(index);

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector vector) {
        checkIndex(index);

        if (vector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("The dimension of the vector must be equal to the number of columns of the matrix. Matrix columns count: "
                    + getColumnsCount() + ". Vector dimension: " + vector.getSize() + ".");
        }

        rows[index] = new Vector(vector);
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("Index out of bounds. (0, " + (getColumnsCount() - 1) + "). Now it's: " + index);
        }

        Vector columnVector = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            columnVector.setComponentByIndex(i, rows[i].getComponentByIndex(index));
        }

        return columnVector;
    }

    public void transpose() {
        Vector[] changedMatrixRows = new Vector[getColumnsCount()];

        for (int i = 0; i < changedMatrixRows.length; i++) {
            changedMatrixRows[i] = new Vector(getColumn(i));
        }

        rows = changedMatrixRows;
    }

    public void multiplyByScalar(double scalar) {
        for (Vector vector : rows) {
            vector.multiply(scalar);
        }
    }

    public double getDeterminant() {
        if (rows.length != getColumnsCount()) {
            throw new UnsupportedOperationException("The determinant can only be found for a square matrix. Now rows count: "
                    + rows.length + ". Columns count: " + getColumnsCount() + ".");
        }

        return getDeterminant(rows);
    }

    private static double getDeterminant(Vector[] matrixRows) {
        int matrixSize = matrixRows.length;

        if (matrixSize == 1) {
            return matrixRows[0].getComponentByIndex(0);
        }

        Vector[] minorMatrix = getVectorsArray(matrixSize - 1, matrixSize - 1);
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

        for (Vector vector : rows) {
            stringBuilder.append(vector).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append('}');

        return stringBuilder.toString();
    }

    public Vector multiplyByVector(Vector vector) {
        if (getColumnsCount() != vector.getSize()) {
            throw new IllegalArgumentException("The columns count in the matrix must match the rows count in the column vector."
                    + "Columns count in matrix: " + getColumnsCount() + ". Vector size: " + vector.getSize() + ".");
        }

        Vector product = new Vector(rows.length);

        for (int i = 0; i < product.getSize(); i++) {
            product.setComponentByIndex(i, Vector.getScalarProduct(rows[i], vector));
        }

        return product;
    }

    private static void checkMatricesDimensions(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rows.length != matrix2.rows.length || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("The dimensions of the matrices must be the same. "
                    + "Matrix1: rows count: " + matrix1.rows.length + ", columns count: " + matrix1.getColumnsCount()
                    + "; Matrix2: rows count: " + matrix2.rows.length + ", columns count: " + matrix2.getColumnsCount() + ".");
        }
    }

    public void add(Matrix matrix) {
        checkMatricesDimensions(this, matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        checkMatricesDimensions(this, matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        checkMatricesDimensions(matrix1, matrix2);

        Matrix resultMatrix = new Matrix(matrix1);

        resultMatrix.add(matrix2);

        return resultMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        checkMatricesDimensions(matrix1, matrix2);

        Matrix resultMatrix = new Matrix(matrix1);

        resultMatrix.subtract(matrix2);

        return resultMatrix;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.rows.length) {
            throw new IllegalArgumentException("The columns count of the first matrix must be equal to the number of rows of the second matrix. Matrix1 columns count: " +
                    matrix1.getColumnsCount() + "; Matrix2 rows count: " + matrix2.rows.length + ".");
        }

        Matrix resultMatrix = new Matrix(matrix1.rows.length, matrix2.getColumnsCount());

        for (int i = 0; i < resultMatrix.rows.length; i++) {
            for (int j = 0; j < resultMatrix.getColumnsCount(); j++) {
                resultMatrix.rows[i].setComponentByIndex(j, Vector.getScalarProduct(matrix1.rows[i], matrix2.getColumn(j)));
            }
        }

        return resultMatrix;
    }
}
