package ru.makhmedov.temperature_model;

public class Fahrenheit implements Scale {
    @Override
    public double convertToCelsius(double temperature) {
        return (temperature - 32) * 5 / 9;
    }

    @Override
    public double convertFromCelsius(double temperature) {
        return (temperature * 9 / 5) + 32;
    }

    @Override
    public String toString() {
        return "Градус Фаренгейта";
    }
}
