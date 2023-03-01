package ru.makhmedov.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int dimension) {
        if (dimension <= 0) {
            throw new IllegalArgumentException("dimension must be > 0. Now it's " + dimension);
        }

        components = new double[dimension];
    }

    public Vector(Vector vector) {
        components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Array length must be > 0. Now it's " + array.length);
        }

        components = Arrays.copyOf(array, array.length);
    }

    public Vector(int dimension, double[] array) {
        if (dimension <= 0) {
            throw new IllegalArgumentException("n must be > 0. Now it's " + dimension);
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
        int maxI = Math.max(components.length, vector.getSize());

        if (components.length < vector.getSize()) {
            components = Arrays.copyOf(components, vector.getSize());
        } else if (components.length > vector.getSize()) {
            maxI = vector.getSize();
        }

        for (int i = 0; i < maxI; i++) {
            this.components[i] += vector.components[i];
        }
    }

    public void subtract(Vector vector) {
        int maxI = Math.max(components.length, vector.getSize());

        if (components.length < vector.getSize()) {
            components = Arrays.copyOf(components, vector.getSize());
        } else if (components.length > vector.getSize()) {
            maxI = vector.getSize();
        }

        for (int i = 0; i < maxI; i++) {
            components[i] -= vector.components[i];
        }
    }

    public void multiply(double scalar) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= scalar;
        }
    }

    public void revert() {
        multiply(-1);
    }

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

        hash = prime * hash + components.length;
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

        if (components.length != v.getSize()) {
            return false;
        }

        return Arrays.equals(components, v.components);
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        int maxSize = Math.max(vector1.getSize(), vector1.getSize());
        Vector resultVector = new Vector(Arrays.copyOf(vector1.components, maxSize));

        resultVector.add(vector2);

        return resultVector;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        int maxSize = Math.max(vector1.getSize(), vector2.getSize());
        Vector resultVector = new Vector(Arrays.copyOf(vector1.components, maxSize));

        resultVector.subtract(vector2);

        return resultVector;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        int minSize = Math.min(vector1.getSize(), vector2.getSize());
        double scalarProduct = 0;

        for (int i = 0; i < minSize; i++) {
            scalarProduct += vector1.components[i] * vector2.components[i];
        }

        return scalarProduct;
    }
}
