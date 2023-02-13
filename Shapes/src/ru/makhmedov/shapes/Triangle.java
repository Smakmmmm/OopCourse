package ru.makhmedov.shapes;

public class Triangle implements Shape {
    private final double x1;
    private final double y1;
    private final double x2;
    private final double y2;
    private final double x3;
    private final double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    @Override
    public double getWidth() {
        return Math.max(x1, Math.max(x2, x3)) - Math.min(x1, Math.min(x2, x3));
    }

    @Override
    public double getHeight() {
        return Math.max(y1, Math.max(y2, y3)) - Math.min(y1, Math.min(y2, y3));
    }

    @Override
    public double getArea() {
        double firstSideLength = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        double secondSideLength = Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));
        double thirdSideLength = Math.sqrt(Math.pow(x3 - x1, 2) + Math.pow(y3 - y1, 2));

        double semiPerimeter = (firstSideLength + secondSideLength + thirdSideLength) / 2;
        return Math.sqrt(semiPerimeter * (semiPerimeter - firstSideLength) *
                (semiPerimeter - secondSideLength) * (semiPerimeter - thirdSideLength));
    }

    @Override
    public double getPerimeter() {
        double firstSideLength = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        double secondSideLength = Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));
        double thirdSideLength = Math.sqrt(Math.pow(x3 - x1, 2) + Math.pow(y3 - y1, 2));

        return firstSideLength + secondSideLength + thirdSideLength;
    }

    @Override
    public String toString() {
        return "Треугольник:\n" +
                "Вершины треугольника: " + "(" + this.x1 + ", " + this.y1 + "), (" +
                                                this.x2 + ", " + this.y2 + "), (" +
                                                this.x3 + ", " + this.y3 + ");\n" +
                "Высота: " + getHeight() + ";\n" +
                "Ширина: " + getWidth() + ";\n" +
                "Площадь: " + getArea() + ";\n" +
                "Периметр: " + getPerimeter() + ".\n";
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Double.hashCode(x1);
        hash = prime * hash + Double.hashCode(y1);
        hash = prime * hash + Double.hashCode(x2);
        hash = prime * hash + Double.hashCode(y2);
        hash = prime * hash + Double.hashCode(x3);
        hash = prime * hash + Double.hashCode(y3);

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

        Triangle t = (Triangle) ob;

        return x1 == t.x1 && x2 == t.x2 && x3 == t.x3 && y1 == t.y1 && y2 == t.y2 && y3 == t.y3;
    }
}
