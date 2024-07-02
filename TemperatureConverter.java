import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverter extends JFrame {
  private JTextField inputField;
  private JComboBox<String> unitComboBox;
  private JTextField celsiusField;
  private JTextField fahrenheitField;
  private JTextField kelvinField;

  public TemperatureConverter() {
    createView();

    setTitle("Temperature Converter");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(500, 400);
    setLocationRelativeTo(null);
    setResizable(true);
  }

  private void createView() {
    JPanel panel = new JPanel();
    getContentPane().add(panel);

    panel.setBackground(Color.LIGHT_GRAY);

    Border border = BorderFactory.createLineBorder(Color.BLACK, 4);
    panel.setBorder(border);

    panel.setLayout(new GridLayout(6, 5, 5, 5));

    JLabel inputLabel = new JLabel("Enter Temperature:");
    panel.add(inputLabel);

    inputField = new JTextField();
    panel.add(inputField);

    JLabel unitLabel = new JLabel("Select Unit:");
    panel.add(unitLabel);

    String[] units = { "Celsius", "Fahrenheit", "Kelvin" };
    unitComboBox = new JComboBox<>(units);
    panel.add(unitComboBox);

    JLabel celsiusLabel = new JLabel("Celsius:");
    panel.add(celsiusLabel);

    celsiusField = new JTextField();
    celsiusField.setEditable(false);
    panel.add(celsiusField);

    JLabel fahrenheitLabel = new JLabel("Fahrenheit:");
    panel.add(fahrenheitLabel);

    fahrenheitField = new JTextField();
    fahrenheitField.setEditable(false);
    panel.add(fahrenheitField);

    JLabel kelvinLabel = new JLabel("Kelvin:");
    panel.add(kelvinLabel);

    kelvinField = new JTextField();
    kelvinField.setEditable(false);
    panel.add(kelvinField);

    JButton convertButton = new JButton("Convert");
    convertButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        convertTemperature();
      }
    });
    panel.add(convertButton);
  }

  private void convertTemperature() {
    String inputText = inputField.getText();
    String selectedUnit = (String) unitComboBox.getSelectedItem();

    try {
      double inputTemp = Double.parseDouble(inputText);

      double celsius = 0;
      double fahrenheit = 0;
      double kelvin = 0;

      switch (selectedUnit) {
        case "Celsius":
          celsius = inputTemp;
          fahrenheit = celsius * 9 / 5 + 32;
          kelvin = celsius + 273.15;
          break;
        case "Fahrenheit":
          fahrenheit = inputTemp;
          celsius = (fahrenheit - 32) * 5 / 9;
          kelvin = celsius + 273.15;
          break;
        case "Kelvin":
          kelvin = inputTemp;
          celsius = kelvin - 273.15;
          fahrenheit = celsius * 9 / 5 + 32;
          break;
      }

      celsiusField.setText(String.format("%.2f", celsius));
      fahrenheitField.setText(String.format("%.2f", fahrenheit));
      kelvinField.setText(String.format("%.2f", kelvin));

    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "Invalid input for temperature",
          "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        new TemperatureConverter().setVisible(true);
      }
    });
  }
}
