package currencyconverter;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Main class to start the Currency Converter application.
 * 
 * @author Rukma Rao (RA2211031010090)
 */
public class Main {
    /**
     * Main method to launch the application
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Set look and feel to system default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Launch the application on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new CurrencyConverter();
        });
    }
}
