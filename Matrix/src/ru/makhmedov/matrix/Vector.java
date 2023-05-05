package ru.makhmedov.matrix;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int dimension) {
        if (dimension <= 0) {
            throw new IllegalArgumentException("Dimension must be > 0. Now it's " + dimension);
        }

        components = new double[dimension];
    }

    @SuppressWarnings("unused")
    public Vector(Vector vector) {
        components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(Vector vector, int dimension) {
        components = Arrays.copyOf(vector.components, dimension);
    }

    public Vector(double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Array length must be > 0. Now it's " + array.length);
        }

        components = Arrays.copyOf(array, array.length);
    }

    public Vector(int dimension, double[] array) {
        if (dimension <= 0) {
            throw new IllegalArgumentException("Dimension must be > 0. Now it's " + dimension);
        }

        components = Arrays.copyOf(array, dimension);
    }

    public int getSize() {
        return components.length;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");

        for (double e : components) {
            stringBuilder.append(e).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());

        return stringBuilder.append("}").toString();
    }

    public void add(Vector vector) {
        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; i++) {
            this.components[i] += vector.components[i];
        }
    }

    public void subtract(Vector vector) {
        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; i++) {
            components[i] -= vector.components[i];
        }
    }

    public void multiply(double scalar) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= scalar;
        }
    }

    @SuppressWarnings("unused")
    public void revert() {
        multiply(-1);
    }

    @SuppressWarnings("unused")
    public double getLength() {
        double sum = 0;

        for (double e : components) {
            sum += e * e;
        }

        return Math.sqrt(sum);
    }

    public double getComponentByIndex(int index) {
        return components[index];
    }

    public void setComponentByIndex(int index, double component) {
        components[index] = component;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Arrays.hashCode(components);

        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Vector v = (Vector) o;

        return Arrays.equals(components, v.components);
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        int maxSize = Math.max(vector1.components.length, vector2.components.length);
        Vector resultVector = new Vector(maxSize, vector1.components);

        resultVector.add(vector2);

        return resultVector;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        int maxSize = Math.max(vector1.components.length, vector2.components.length);
        Vector resultVector = new Vector(maxSize, vector1.components);

        resultVector.subtract(vector2);

        return resultVector;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        int minSize = Math.min(vector1.components.length, vector2.components.length);
        double scalarProduct = 0;

        for (int i = 0; i < minSize; i++) {
            scalarProduct += vector1.components[i] * vector2.components[i];
        }

        return scalarProduct;
    }
}
