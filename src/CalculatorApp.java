import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorApp extends JFrame implements ActionListener {
    private JComboBox<Integer> firstNumberComboBox;
    private JSlider secondNumberSlider;
    private JTextField operationTextField;

    public CalculatorApp() {
        setTitle("Kalkulator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1400, 400);
        setLocationRelativeTo(null);

        JPanel actionPanel = new JPanel(new GridLayout(1, 3));

        firstNumberComboBox = new JComboBox<>();
        for (int i = 0; i <= 10000; i++) {
            firstNumberComboBox.addItem(i);
        }
        actionPanel.add(firstNumberComboBox);

        secondNumberSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        secondNumberSlider.setMajorTickSpacing(10);
        secondNumberSlider.setMinorTickSpacing(1);
        secondNumberSlider.setPaintTicks(true);
        secondNumberSlider.setPaintLabels(true);
        actionPanel.add(secondNumberSlider);

        operationTextField = new JTextField();
        actionPanel.add(operationTextField);

        add(actionPanel, BorderLayout.NORTH);

        JButton calculateButton = new JButton("Oblicz");
        calculateButton.addActionListener(this);
        add(calculateButton, BorderLayout.SOUTH);

        secondNumberSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    int value = source.getValue();
                    operationTextField.setText(String.valueOf(value));
                }
            }
        });

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int firstNumber = (Integer) firstNumberComboBox.getSelectedItem();
        int secondNumber = secondNumberSlider.getValue();
        String operation = operationTextField.getText();

        double result = 0;
        switch (operation.toLowerCase()) {
            case "dodawanie":
                result = firstNumber + secondNumber;
                break;
            case "odejmowanie":
                result = firstNumber - secondNumber;
                break;
            case "mnorzenie":
                result = firstNumber * secondNumber;
                break;
            case "dzielenie":
                if (secondNumber != 0) {
                    result = (double) firstNumber / secondNumber;
                } else {
                    JOptionPane.showMessageDialog(this, "Można dzielić przez zero!!!", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                break;
            default:
                JOptionPane.showMessageDialog(this, "Niepoprawna operacja", "Błąd", JOptionPane.ERROR_MESSAGE);
                return;
        }
        JOptionPane.showMessageDialog(this, "Wynik: " + result, "Wynik", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CalculatorApp();
            }
        });
    }
}
