package ru.makhmedov.temperature_model;

public interface Model {
    double convertTemperature(Scale inputScale, Scale outputScale, double inputTemperature);
}
