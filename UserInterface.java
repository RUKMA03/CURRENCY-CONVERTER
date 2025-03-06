import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Currency Converter");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);
        
        frame.setVisible(true);
    }
    
    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);
        
        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(10, 20, 80, 25);
        panel.add(amountLabel);
        
        JTextField amountText = new JTextField(20);
        amountText.setBounds(100, 20, 165, 25);
        panel.add(amountText);
        
        JLabel fromLabel = new JLabel("From:");
        fromLabel.setBounds(10, 50, 80, 25);
        panel.add(fromLabel);
        
        JComboBox<String> fromCurrency = new JComboBox<>(new String[]{"USD", "EUR", "INR", "GBP"});
        fromCurrency.setBounds(100, 50, 165, 25);
        panel.add(fromCurrency);
        
        JLabel toLabel = new JLabel("To:");
        toLabel.setBounds(10, 80, 80, 25);
        panel.add(toLabel);
        
        JComboBox<String> toCurrency = new JComboBox<>(new String[]{"USD", "EUR", "INR", "GBP"});
        toCurrency.setBounds(100, 80, 165, 25);
        panel.add(toCurrency);
        
        JButton convertButton = new JButton("Convert");
        convertButton.setBounds(10, 110, 150, 25);
        panel.add(convertButton);
        
        JLabel resultLabel = new JLabel("Converted Amount: ");
        resultLabel.setBounds(10, 140, 300, 25);
        panel.add(resultLabel);
        
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(amountText.getText());
                String from = (String) fromCurrency.getSelectedItem();
                String to = (String) toCurrency.getSelectedItem();
                
                double rate = ExchangeRateAPI.getExchangeRate(from, to);
                if (rate > 0) {
                    double convertedAmount = amount * rate;
                    resultLabel.setText("Converted Amount: " + convertedAmount + " " + to);
                } else {
                    resultLabel.setText("Error fetching exchange rate.");
                }
            }
        });
    }
}