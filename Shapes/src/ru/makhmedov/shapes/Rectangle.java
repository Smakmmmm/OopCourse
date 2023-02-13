package ru.makhmedov.shapes;

public class Rectangle implements Shape {
    private final double height;
    private final double width;

    public Rectangle(double height, double width) {
        this.height = height;
        this.width = width;
    }

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
        return 2 * height + 2 * width;
    }

    @Override
    public String toString() {
        return "Прямоугольник:\n" +
                "Высота: " + this.height + ";\n" +
                "Ширина: " + this.width + ";\n" +
                "Площадь: " + getArea() + ";\n" +
                "Периметр: " + getPerimeter() + ".\n";
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
    public boolean equals(Object ob) {
        if (ob == this) {
            return true;
        }

        if (ob == null || ob.getClass() != getClass()) {
            return false;
        }

        Rectangle r = (Rectangle) ob;

        return height == r.height && width == r.width;
    }
}
