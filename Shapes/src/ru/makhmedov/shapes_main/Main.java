package ru.makhmedov.shapes_main;

import ru.makhmedov.shapes.*;

import java.util.Arrays;

public class Main {
    public static Shape getMaxFigure(Shape[] shapes) {
        Arrays.sort(shapes, new AreaComparator());

        return shapes[0];
    }

    public static Shape getSecondMaxFigure(Shape[] shapes) {
        Arrays.sort(shapes, new AreaComparator());

        return shapes[1];
    }

    public static void main(String[] args) {
        Shape[] shapes = {
                new Square(4),
                new Triangle(1, 1, 4, 7, 7, 3),
                new Rectangle(5, 12),
                new Circle(6),
                new Square(5.4),
                new Rectangle(3, 8),
                new Circle(7.3),
                new Triangle(2, 7, 4, 2, 9, 6)
        };

        System.out.println(getMaxFigure(shapes));
        System.out.println(getSecondMaxFigure(shapes));
    }
}
