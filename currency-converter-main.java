package currencyconverter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;

/**
 * Main Currency Converter application class.
 * Implements a GUI for converting between different currencies.
 * 
 * @author Rukma Rao (RA2211031010090)
 */
public class CurrencyConverter extends JFrame implements ActionListener {
    // GUI Components
    private JLabel amountLabel, fromLabel, toLabel, resultLabel;
    private JTextField amountField, resultField;
    private JComboBox<String> fromComboBox, toComboBox;
    private JButton convertButton, clearButton, submitButton;
    private JPanel mainPanel, buttonPanel;
    
    // Currency data
    private String[] currencies = {
        "USD (US Dollar)", 
        "EUR (Euro)", 
        "GBP (British Pound)", 
        "JPY (Japanese Yen)", 
        "CAD (Canadian Dollar)", 
        "AUD (Australian Dollar)", 
        "CHF (Swiss Franc)", 
        "CNY (Chinese Yuan)", 
        "INR (Indian Rupee)"
    };
    
    // Exchange rates relative to USD
    private double[] exchangeRates = {
        1.00,    // USD
        0.85,    // EUR
        0.75,    // GBP
        110.33,  // JPY
        1.25,    // CAD
        1.33,    // AUD
        0.92,    // CHF
        6.47,    // CNY
        74.29    // INR
    };
    
    // Database connection
    private DatabaseManager dbManager;

    /**
     * Constructor for the Currency Converter application
     */
    public CurrencyConverter() {
        // Set up the frame
        super("Currency Converter");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
        
        // Initialize database connection
        dbManager = new DatabaseManager();
        
        // Create and set up GUI components
        initializeComponents();
        
        // Layout components
        layoutComponents();
        
        // Add action listeners
        addEventHandlers();
        
        // Make the frame visible
        setVisible(true);
    }
    
    /**
     * Initialize all GUI components
     */
    private void initializeComponents() {
        // Labels
        amountLabel = new JLabel("Amount:");
        fromLabel = new JLabel("From:");
        toLabel = new JLabel("To:");
        resultLabel = new JLabel("Converted Amount:");
        
        // Text fields
        amountField = new JTextField(10);
        resultField = new JTextField(10);
        resultField.setEditable(false);
        
        // Combo boxes
        fromComboBox = new JComboBox<>(currencies);
        toComboBox = new JComboBox<>(currencies);
        toComboBox.setSelectedIndex(1); // Default to EUR
        
        // Buttons
        convertButton = new JButton("Convert");
        clearButton = new JButton("Clear");
        submitButton = new JButton("Submit");
        
        // Panels
        mainPanel = new JPanel(new GridBagLayout());
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    }
    
    /**
     * Layout the components using GridBagLayout
     */
    private void layoutComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Add components to main panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(amountLabel, gbc);
        
        gbc.gridx = 1;
        mainPanel.add(amountField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(fromLabel, gbc);
        
        gbc.gridx = 1;
        mainPanel.add(fromComboBox, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(toLabel, gbc);
        
        gbc.gridx = 1;
        mainPanel.add(toComboBox, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(resultLabel, gbc);
        
        gbc.gridx = 1;
        mainPanel.add(resultField, gbc);
        
        // Add buttons to button panel
        buttonPanel.add(convertButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(submitButton);
        
        // Add panels to frame
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    /**
     * Add event handlers to interactive components
     */
    private void addEventHandlers() {
        convertButton.addActionListener(this);
        clearButton.addActionListener(this);
        submitButton.addActionListener(this);
    }
    
    /**
     * Handle button click events
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == convertButton) {
            convertCurrency();
        } else if (e.getSource() == clearButton) {
            clearFields();
        } else if (e.getSource() == submitButton) {
            submitConversion();
        }
    }
    
    /**
     * Convert the currency based on user input
     */
    private void convertCurrency() {
        try {
            // Get the amount to convert
            double amount = Double.parseDouble(amountField.getText());
            
            // Get selected currencies
            int fromIndex = fromComboBox.getSelectedIndex();
            int toIndex = toComboBox.getSelectedIndex();
            
            // Convert to common base (USD), then to target currency
            double result = amount / exchangeRates[fromIndex] * exchangeRates[toIndex];
            
            // Format and display the result
            DecimalFormat df = new DecimalFormat("#,##0.00");
            resultField.setText(df.format(result));
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "Please enter a valid number for the amount.",
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Clear all input and result fields
     */
    private void clearFields() {
        amountField.setText("");
        resultField.setText("");
        fromComboBox.setSelectedIndex(0);
        toComboBox.setSelectedIndex(1);
    }
    
    /**
     * Submit the conversion to database
     */
    private void submitConversion() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            
            if (resultField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Please convert first before submitting.",
                    "Input Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            double convertedAmount = Double.parseDouble(
                resultField.getText().replace(",", ""));
            
            String fromCurrency = currencies[fromComboBox.getSelectedIndex()];
            String toCurrency = currencies[toComboBox.getSelectedIndex()];
            
            // Store in database
            boolean success = storeConversion(amount, fromCurrency, toCurrency, convertedAmount);
            
            if (success) {
                JOptionPane.showMessageDialog(this, 
                    "Conversion submitted successfully!",
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Failed to submit conversion.",
                    "Database Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "Please enter a valid number for the amount and convert before submitting.",
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Store conversion details in the database
     */
    private boolean storeConversion(double amount, String fromCurrency, 
                                 String toCurrency, double convertedAmount) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = dbManager.getConnection();
            String sql = "INSERT INTO conversions (amount, from_currency, to_currency, " +
                        "converted_amount, conversion_date) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)";
            
            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, amount);
            stmt.setString(2, fromCurrency);
            stmt.setString(3, toCurrency);
            stmt.setDouble(4, convertedAmount);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Main method to start the application
     */
    public static void main(String[] args) {
        // Set look and feel to system default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Create application on EDT
        SwingUtilities.invokeLater(() -> {
            new CurrencyConverter();
        });
    }
}
