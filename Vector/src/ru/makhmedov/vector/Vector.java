package ru.makhmedov.vector;

import java.util.Arrays;

public class Vector {
    private final int n;
    private double[] vector;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be > 0");
        }

        this.n = n;
        vector = new double[n];
    }

    public Vector(Vector vector) {
        n = vector.n;
        this.vector = Arrays.copyOf(vector.vector, n);
    }

    public Vector(double[] array) {
        n = array.length;
        vector = Arrays.copyOf(array, n);
    }

    public Vector(int n, double[] array) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be > 0");
        }

        this.n = n;
        vector = Arrays.copyOf(array, n);
    }

    public int getSize() {
        return n;
    }

    @Override
    public String toString() {
        StringBuilder vector = new StringBuilder();

        vector.append("{");

        for (int i = 0; ; i++) {
            vector.append(this.vector[i]);

            if (i == n - 1) {
                return vector.append("}").toString();
            }

            vector.append(", ");
        }
    }

    public void add(Vector vector) {
        int maxSize = Math.max(n, vector.n);

        if (n < vector.n) {
            this.vector = Arrays.copyOf(this.vector, maxSize);
        } else if (n > vector.n) {
            vector.vector = Arrays.copyOf(vector.vector, maxSize);
        }

        for (int i = 0; i < maxSize; i++) {
            this.vector[i] += vector.vector[i];
        }
    }

    public void subtract(Vector vector) {
        int maxSize = Math.max(n, vector.n);

        if (n < vector.n) {
            this.vector = Arrays.copyOf(this.vector, maxSize);
        } else if (n > vector.n) {
            vector.vector = Arrays.copyOf(vector.vector, maxSize);
        }

        for (int i = 0; i < maxSize; i++) {
            this.vector[i] -= vector.vector[i];
        }
    }

    public void multiply(double scalar) {
        for (int i = 0; i < n; i++) {
            vector[i] *= scalar;
        }
    }

    public void revert() {
        for (int i = 0; i < n; i++) {
            vector[i] *= -1;
        }
    }

    public double getLength() {
        double length = 0;

        for (int i = 0; i < n; i++) {
            length += Math.pow(vector[i], 2);
        }

        return Math.sqrt(length);
    }

    public double getComponentByIndex(int index) {
        return vector[index];
    }

    public void setComponentByIndex(int index, double component) {
        vector[index] = component;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + n;
        hash = prime * hash + Arrays.hashCode(vector);

        return hash;
    }

    @Override
    public boolean equals(Object ob) {
        if (ob == this) {
            return true;
        }

        if (ob == null || ob.getClass() != getClass()) {
            return false;
        }

        Vector v = (Vector) ob;

        if (n != v.n) {
            return false;
        }

        for (int i = 0; i < n; i++) {
            if (vector[i] != v.vector[i]) {
                return false;
            }
        }

        return true;
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        int maxSize = Math.max(vector1.n, vector2.n);

        if (vector1.n < vector2.n) {
            vector1.vector = Arrays.copyOf(vector1.vector, maxSize);
        } else if (vector1.n > vector2.n) {
            vector2.vector = Arrays.copyOf(vector2.vector, maxSize);
        }

        double[] resultVector = new double[maxSize];

        for (int i = 0; i < maxSize; i++) {
            resultVector[i] = vector1.vector[i] + vector2.vector[i];
        }

        return new Vector(maxSize, resultVector);
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        int maxSize = Math.max(vector1.n, vector2.n);

        if (vector1.n < vector2.n) {
            vector1.vector = Arrays.copyOf(vector1.vector, maxSize);
        } else if (vector1.n > vector2.n) {
            vector2.vector = Arrays.copyOf(vector2.vector, maxSize);
        }

        double[] resultVector = new double[maxSize];

        for (int i = 0; i < maxSize; i++) {
            resultVector[i] = vector1.vector[i] - vector2.vector[i];
        }

        return new Vector(maxSize, resultVector);
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        int minSize = Math.min(vector1.n, vector2.n);
        double scalarProduct = 0;

        for (int i = 0; i < minSize; i++) {
            scalarProduct += vector1.vector[i] * vector2.vector[i];
        }

        return scalarProduct;
    }
}
