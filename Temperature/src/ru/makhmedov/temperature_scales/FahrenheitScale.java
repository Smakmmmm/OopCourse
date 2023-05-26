package ru.makhmedov.temperature_scales;

import ru.makhmedov.temperature_model.Scale;

public class FahrenheitScale implements Scale {
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
