package ru.makhmedov.temperature_main;

import ru.makhmedov.temperature_model.*;
import ru.makhmedov.temperature_scales.CelsiusScale;
import ru.makhmedov.temperature_scales.FahrenheitScale;
import ru.makhmedov.temperature_scales.KelvinScale;
import ru.makhmedov.temperature_view.TemperatureConverterView;
import ru.makhmedov.temperature_view.View;

public class Main {
    public static void main(String[] args) {
        Scale[] scales = {new CelsiusScale(), new KelvinScale(), new FahrenheitScale()};
        Model model = new TemperatureConverterModel(scales);
        View view = new TemperatureConverterView(model);
        view.start();
    }
}
