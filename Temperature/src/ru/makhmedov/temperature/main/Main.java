package ru.makhmedov.temperature.main;

import ru.makhmedov.temperature.model.Model;
import ru.makhmedov.temperature.model.TemperatureConverterModel;
import ru.makhmedov.temperature.model.scales.FahrenheitScale;
import ru.makhmedov.temperature.model.scales.KelvinScale;
import ru.makhmedov.temperature.model.scales.Scale;
import ru.makhmedov.temperature.model.scales.CelsiusScale;

import ru.makhmedov.temperature.view.TemperatureConverterView;
import ru.makhmedov.temperature.view.View;

public class Main {
    public static void main(String[] args) {
        Scale[] scales = {
                new CelsiusScale(),
                new KelvinScale(),
                new FahrenheitScale()
        };

        Model model = new TemperatureConverterModel(scales);
        View view = new TemperatureConverterView(model);
        view.start();
    }
}
