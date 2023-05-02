package ru.makhmedov.temperature_view;

import ru.makhmedov.temperature_model.Scale;

public interface View {
    void start();

    Scale getInputScaleComboBox();

    Scale getOutputScaleComboBox();

    double getInputTemperature();

    void updateTemperature(double outputTemperature);

    void showError(String message);
}
