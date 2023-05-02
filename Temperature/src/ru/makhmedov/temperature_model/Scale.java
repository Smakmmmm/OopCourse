package ru.makhmedov.temperature_model;

public interface Scale {
    double convertToCelsius(double temperature);

    double convertFromCelsius(double temperature);
}
