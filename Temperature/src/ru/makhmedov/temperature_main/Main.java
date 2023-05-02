package ru.makhmedov.temperature_main;

import ru.makhmedov.temperature_model.*;
import ru.makhmedov.temperature_view.TemperatureConverterView;
import ru.makhmedov.temperature_view.View;

public class Main {
    public static void main(String[] args) {
        TemperatureConverterModel model = new TemperatureConverterModel(new Scale[]{new Celsius(), new Kelvin(), new Fahrenheit()});
        View temperatureConverter = new TemperatureConverterView(model);
        temperatureConverter.start();
    }
}
