package ru.makhmedov.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public Range() {
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

    public Range getRangeCrossing(Range range1, Range range2) {
        if (range1.to <= range2.from || range1.from >= range2.to) {
            return null;
        }

        return new Range(Math.max(range1.from, range2.from), Math.min(range1.to, range2.to));
    }

    public Range[] getRangeCombining(Range range1, Range range2) {
        if (range1.to < range2.from || range1.from > range2.to) {
            return new Range[]{range1, range2};
        }

        Range resultRange = new Range(Math.min(range1.from, range2.from), Math.max(range1.to, range2.to));

        return new Range[]{resultRange};
    }

    public Range[] getRangeDifference(Range range1, Range range2) {
        if ((range1.to <= range2.from || range1.from >= range2.to) ||
                (range1.from >= range2.from && range1.to <= range2.to)) {
            return null;
        }

        if (range1.from < range2.from && range1.to > range2.to) {
            Range resultRange1 = new Range(range1.from, range2.from);
            Range resultRange2 = new Range(range2.to, range1.to);

            return new Range[]{resultRange1, resultRange2};
        }

        if (range1.from < range2.from) {
            return new Range[]{new Range(range1.from, range2.from)};
        }

        return new Range[]{new Range(range2.to, range1.to)};
    }
}
