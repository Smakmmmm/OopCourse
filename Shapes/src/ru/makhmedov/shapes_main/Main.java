package ru.makhmedov.shapes_main;

import ru.makhmedov.shapes_comparators.AreaComparator;
import ru.makhmedov.shapes_comparators.PerimeterComparator;
import ru.makhmedov.shapes.*;

import java.util.Arrays;

public class Main {
    public static Shape getShapeWithMaxArea(Shape[] shapes) {
        if (shapes.length == 0) {
            return null;
        }

        Arrays.sort(shapes, new AreaComparator());

        return shapes[shapes.length - 1];
    }

    public static Shape getShapeWithSecondLargestPerimeter(Shape[] shapes) {
        if (shapes.length == 0) {
            return null;
        }

        if (shapes.length == 1) {
            return shapes[0];
        }

        Arrays.sort(shapes, new PerimeterComparator());

        return shapes[shapes.length - 2];
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

        System.out.println(getShapeWithMaxArea(shapes));
        System.out.println(getShapeWithSecondLargestPerimeter(shapes));

        Shape[] shapes1 = {};
        System.out.println(getShapeWithMaxArea(shapes1));
    }
}
