package ru.makhmedov.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return (number >= from) && (number <= to);
    }

    public Range getIntersection(Range range2) {
        if (this.to <= range2.from || this.from >= range2.to) {
            return null;
        }

        return new Range(Math.max(this.from, range2.from), Math.min(this.to, range2.to));
    }

    public Range[] getUnion(Range range2) {
        if (this.to < range2.from || this.from > range2.to) {
            return new Range[]{new Range(this.from, this.to), new Range(range2.from, range2.to)};
        }

        return new Range[]{new Range(Math.min(this.from, range2.from), Math.max(this.to, range2.to))};
    }

    public Range[] getDifference(Range range) {
        if (this.from >= range.from && this.to <= range.to) {
            return new Range[]{};
        }

        if (this.to <= range.from || this.from >= range.to) {
            return new Range[]{new Range(this.from, this.to)};
        }

        if (this.from < range.from && this.to > range.to) {
            return new Range[]{new Range(this.from, range.from), new Range(range.to, this.to)};
        }

        if (this.from < range.from) {
            return new Range[]{new Range(this.from, range.from)};
        }

        return new Range[]{new Range(range.to, this.to)};
    }

    @Override
    public String toString() {
        return "(" + this.from + ", " + this.to + ")";
    }

    public static String toString(Range[] array) {
        int iMax = array.length - 1;

        if (iMax == -1) {
            return "Пустое множество";
        }

        StringBuilder b = new StringBuilder();
        for (int i = 0; ; i++) {
            b.append(array[i]);

            if (i == iMax) {
                return b.toString();
            }

            b.append(", ");
        }
    }
}
