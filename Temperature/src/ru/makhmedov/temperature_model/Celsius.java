package ru.makhmedov.temperature_model;

public class Celsius implements Scale {
    @Override
    public double convertToCelsius(double temperature) {
        return temperature;
    }

    @Override
    public double convertFromCelsius(double temperature) {
        return temperature;
    }

    @Override
    public String toString() {
        return "Градус Цельсия";
    }
}
