package ru.makhmedov.temperature.view;

import ru.makhmedov.temperature.model.scales.Scale;

public interface View {
    void start();

    Scale getInputScale();

    Scale getOutputScale();

    double getInputTemperature();

    void setOutputTemperature(double outputTemperature);

    void showError(String message);
}
