package ru.makhmedov.shapes;

public record Triangle(double x1, double y1, double x2, double y2, double x3, double y3) implements Shape {
    @Override
    public double getWidth() {
        return Math.max(x1, Math.max(x2, x3)) - Math.min(x1, Math.min(x2, x3));
    }

    @Override
    public double getHeight() {
        return Math.max(y1, Math.max(y2, y3)) - Math.min(y1, Math.min(y2, y3));
    }

    private static double getSideLength(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    @Override
    public double getArea() {
        double side1Length = getSideLength(x1, y1, x2, y2);
        double side2Length = getSideLength(x2, y2, x3, y3);
        double side3Length = getSideLength(x1, y1, x3, y3);

        double semiPerimeter = (side1Length + side2Length + side3Length) / 2;
        return Math.sqrt(semiPerimeter * (semiPerimeter - side1Length) *
                (semiPerimeter - side2Length) * (semiPerimeter - side3Length));
    }

    @Override
    public double getPerimeter() {
        return getSideLength(x1, y1, x2, y2) + getSideLength(x2, y2, x3, y3) + getSideLength(x1, y1, x3, y3);
    }

    @Override
    public String toString() {
        return "Треугольник: " +
                "Вершины треугольника: " + "(" + x1 + ", " + y1 + "), (" +
                x2 + ", " + y2 + "), (" +
                x3 + ", " + y3 + "); " +
                "Высота: " + getHeight() + "; " +
                "Ширина: " + getWidth() + "; " +
                "Площадь: " + getArea() + "; " +
                "Периметр: " + getPerimeter() + ".";
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
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Triangle t = (Triangle) o;

        return x1 == t.x1 && x2 == t.x2 && x3 == t.x3 && y1 == t.y1 && y2 == t.y2 && y3 == t.y3;
    }
}
