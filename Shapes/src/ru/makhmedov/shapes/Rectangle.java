package ru.makhmedov.shapes;

public record Rectangle(double width, double height) implements Shape {
    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getArea() {
        return height * width;
    }

    @Override
    public double getPerimeter() {
        return 2 * (height + width);
    }

    @Override
    public String toString() {
        return "Прямоугольник: " +
                "Высота: " + height + "; " +
                "Ширина: " + width + "; " +
                "Площадь: " + getArea() + "; " +
                "Периметр: " + getPerimeter() + ".";
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Double.hashCode(height);
        hash = prime * hash + Double.hashCode(width);

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

        Rectangle r = (Rectangle) o;

        return height == r.height && width == r.width;
    }
}
