package ru.makhmedov.temperature_view;

import ru.makhmedov.temperature_model.*;

import javax.swing.*;
import java.awt.*;

public class TemperatureConverterView implements View {
    private final TemperatureConverterModel model;

    private JTextField inputTextField;
    private JTextField outputTextField;
    private JButton convertButton;
    private JComboBox<Scale> inputScaleComboBox;
    private JComboBox<Scale> outputScaleComboBox;

    private double outputTemperature;

    public TemperatureConverterView(TemperatureConverterModel model) {
        this.model = model;
    }

    @Override
    public void start() {
        SwingUtilities.invokeLater(() -> {
            inputTextField = new JTextField(13);
            inputTextField.setHorizontalAlignment(JTextField.CENTER);
            outputTextField = new JTextField(13);
            outputTextField.setEditable(false);
            outputTextField.setHorizontalAlignment(JTextField.CENTER);

            inputScaleComboBox = new JComboBox<>(model.scales());
            outputScaleComboBox = new JComboBox<>(model.scales());

            convertButton = new JButton("Конвертировать");
            convertButton.addActionListener(e -> {
                try {
                    outputTemperature = model.convertTemperature(getInputScaleComboBox(),
                            getOutputScaleComboBox(),
                            getInputTemperature());
                    updateTemperature(outputTemperature);
                } catch (NumberFormatException exception) {
                    showError("Температура должна быть числом!");
                }
            });

            JFrame frame = new JFrame("Конвертер температур");
            frame.setSize(500, 300);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            frame.setLayout(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();

            constraints.ipadx = 51;
            constraints.anchor = GridBagConstraints.NORTH;
            constraints.fill = GridBagConstraints.NONE;
            constraints.gridheight = 1;
            constraints.gridwidth = GridBagConstraints.REMAINDER;
            constraints.gridx = GridBagConstraints.WEST;
            constraints.gridy = GridBagConstraints.NORTH;
            constraints.insets = new Insets(20, 0, 0, 200);

            frame.add(inputTextField, constraints);
            constraints.gridy += 1;

            frame.add(inputScaleComboBox, constraints);

            constraints.insets = new Insets(20, 200, 0, 0);
            constraints.gridy = GridBagConstraints.NORTH;
            constraints.gridx = GridBagConstraints.EAST;
            frame.add(outputTextField, constraints);
            constraints.gridy = 12;
            frame.add(outputScaleComboBox, constraints);

            constraints.gridx = GridBagConstraints.CENTER;
            constraints.gridy = GridBagConstraints.CENTER;
            constraints.insets = new Insets(0, 0, 25, 0);
            frame.add(convertButton, constraints);
        });
    }

    @Override
    public Scale getInputScaleComboBox() {
        return (Scale) inputScaleComboBox.getSelectedItem();
    }

    @Override
    public Scale getOutputScaleComboBox() {
        return (Scale) outputScaleComboBox.getSelectedItem();
    }

    @Override
    public double getInputTemperature() {
        return Double.parseDouble(inputTextField.getText());
    }

    @Override
    public void updateTemperature(double outputTemperature) {
        outputTextField.setText(String.valueOf(outputTemperature));
    }

    @Override
    public void showError(String message) {
        JOptionPane.showMessageDialog(
                convertButton,
                message,
                "Ошибка",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
