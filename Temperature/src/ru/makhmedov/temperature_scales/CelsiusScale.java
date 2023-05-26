package ru.makhmedov.temperature_scales;

import ru.makhmedov.temperature_model.Scale;

public class CelsiusScale implements Scale {
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
