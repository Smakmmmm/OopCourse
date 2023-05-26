package ru.makhmedov.temperature_model;

public record TemperatureConverterModel(Scale[] scales) implements Model {
    @Override
    public double convertTemperature(Scale inputScale, Scale outputScale, double inputTemperature) {
        double temperatureInCelsius = inputScale.convertToCelsius(inputTemperature);

        return outputScale.convertFromCelsius(temperatureInCelsius);
    }

    @Override
    public Scale[] getScales() {
        return scales;
    }
}
