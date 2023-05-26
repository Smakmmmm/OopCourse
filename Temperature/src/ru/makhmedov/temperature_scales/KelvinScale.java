package ru.makhmedov.temperature_scales;

import ru.makhmedov.temperature_model.Scale;

public class KelvinScale implements Scale {
    @Override
    public double convertToCelsius(double temperature) {
        return temperature - 273.15;
    }

    @Override
    public double convertFromCelsius(double temperature) {
        return temperature + 273.15;
    }

    @Override
    public String toString() {
        return "Кельвин";
    }
}
