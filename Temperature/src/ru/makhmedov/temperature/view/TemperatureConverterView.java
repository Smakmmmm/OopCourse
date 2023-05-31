package ru.makhmedov.temperature.view;

import ru.makhmedov.temperature.model.Model;
import ru.makhmedov.temperature.model.scales.Scale;

import javax.swing.*;
import java.awt.*;

public class TemperatureConverterView implements View {
    private final Model model;

    private JTextField inputTextField;
    private JTextField outputTextField;
    private JButton convertButton;
    private JComboBox<Scale> inputScaleComboBox;
    private JComboBox<Scale> outputScaleComboBox;

    public TemperatureConverterView(Model model) {
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

            inputScaleComboBox = new JComboBox<>(model.getScales());
            outputScaleComboBox = new JComboBox<>(model.getScales());

            convertButton = new JButton("Конвертировать");
            convertButton.addActionListener(e -> {
                try {
                    setOutputTemperature(model.convertTemperature(
                            getInputScale(),
                            getOutputScale(),
                            getInputTemperature()));
                } catch (NumberFormatException exception) {
                    showError("Температура должна быть числом!");
                }
            });

            JFrame frame = new JFrame("Конвертер температур");
            frame.setSize(500, 300);
            frame.setMinimumSize(new Dimension(500, 300));
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
            constraints.gridx = 17;
            constraints.gridy = 11;

            constraints.insets = new Insets(0, 50, 0, 200);
            frame.add(new JLabel("Входная температура"), constraints);

            constraints.insets = new Insets(0, 250, 0, 0);
            frame.add(new JLabel("Выходная температура"), constraints);

            constraints.insets = new Insets(20, 0, 0, 200);
            frame.add(inputTextField, constraints);
            constraints.gridy++;

            frame.add(inputScaleComboBox, constraints);

            constraints.insets = new Insets(20, 200, 0, 0);
            constraints.gridy = 11;
            constraints.gridx = 13;
            frame.add(outputTextField, constraints);
            constraints.gridy = 12;
            frame.add(outputScaleComboBox, constraints);

            constraints.gridx = 10;
            constraints.gridy = 10;
            constraints.insets = new Insets(0, 0, 25, 0);
            frame.add(convertButton, constraints);
        });
    }

    @Override
    public Scale getInputScale() {
        return (Scale) inputScaleComboBox.getSelectedItem();
    }

    @Override
    public Scale getOutputScale() {
        return (Scale) outputScaleComboBox.getSelectedItem();
    }

    @Override
    public double getInputTemperature() {
        return Double.parseDouble(inputTextField.getText());
    }

    @Override
    public void setOutputTemperature(double outputTemperature) {
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
