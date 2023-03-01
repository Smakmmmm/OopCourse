package ru.makhmedov.shapes;

public record Circle(double radius) implements Shape {
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
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return "Окружность: " +
                "Радиус: " + radius + "; " +
                "Диаметр (ширина и высота): " + getHeight() + "; " +
                "Площадь: " + getArea() + "; " +
                "Длина окружности (периметр): " + getPerimeter() + ".";
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Double.hashCode(radius);

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

        Circle c = (Circle) o;

        return radius == c.radius;
    }
}
