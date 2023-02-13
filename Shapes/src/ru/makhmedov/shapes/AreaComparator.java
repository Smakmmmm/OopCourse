package ru.makhmedov.shapes;

import java.util.Comparator;

public class AreaComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape sh1, Shape sh2) {
        return Double.compare(sh2.getArea(), sh1.getArea());
    }
}
