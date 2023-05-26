package ru.makhmedov.temperature_view;

import ru.makhmedov.temperature_model.Scale;

public interface View {
    void start();

    Scale getInputScale();

    Scale getOutputScale();

    double getInputTemperature();

    void setOutputTemperature(double outputTemperature);

    void showError(String message);
}
