package ru.makhmedov.shapes;

public class Circle implements Shape {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getWidth() {
        return 2 * radius;
    }

    @Override
    public double getHeight() {
        return 2 * radius;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return "Окружность:\n" +
                "Радиус: " + this.radius + ";\n" +
                "Диаметр (ширина и высота): " + getHeight() + ";\n" +
                "Площадь: " + getArea() + ";\n" +
                "Длина окружности (периметр): " + getPerimeter() + ".\n";
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Double.hashCode(radius);

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

        Circle c = (Circle) ob;

        return radius == c.radius;
    }
}
