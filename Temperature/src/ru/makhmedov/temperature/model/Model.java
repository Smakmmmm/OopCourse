package ru.makhmedov.temperature.model;

import ru.makhmedov.temperature.model.scales.Scale;

public interface Model {
    double convertTemperature(Scale inputScale, Scale outputScale, double inputTemperature);

    Scale[] getScales();
}
