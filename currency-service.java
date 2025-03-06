package currencyconverter;

import java.util.HashMap;
import java.util.Map;

/**
 * Service class to handle currency conversion logic.
 * 
 * @author Rukma Rao (RA2211031010090)
 */
public class CurrencyService {
    // Map of currencies and their exchange rates relative to USD
    private Map<String, Double> exchangeRates;
    
    /**
     * Constructor - initialize exchange rates
     */
    public CurrencyService() {
        exchangeRates = new HashMap<>();
        initializeExchangeRates();
    }
    
    /**
     * Initialize exchange rates with predefined values
     */
    private void initializeExchangeRates() {
        // Exchange rates relative to USD
        exchangeRates.put("USD (US Dollar)", 1.00);
        exchangeRates.put("EUR (Euro)", 0.85);
        exchangeRates.put("GBP (British Pound)", 0.75);
        exchangeRates.put("JPY (Japanese Yen)", 110.33);
        exchangeRates.put("CAD (Canadian Dollar)", 1.25);
        exchangeRates.put("AUD (Australian Dollar)", 1.33);
        exchangeRates.put("CHF (Swiss Franc)", 0.92);
        exchangeRates.put("CNY (Chinese Yuan)", 6.47);
        exchangeRates.put("INR (Indian Rupee)", 74.29);
    }
    
    /**
     * Convert an amount from one currency to another
     * 
     * @param amount Amount to convert
     * @param fromCurrency Source currency
     * @param toCurrency Target currency
     * @return Converted amount
     * @throws IllegalArgumentException if currencies are not supported
     */
    public double convert(double amount, String fromCurrency, String toCurrency) {
        // Check if currencies are supported
        if (!exchangeRates.containsKey(fromCurrency) || !exchangeRates.containsKey(toCurrency)) {
            throw new IllegalArgumentException("Unsupported currency");
        }
        
        // Get exchange rates
        double fromRate = exchangeRates.get(fromCurrency);
        double toRate = exchangeRates.get(toCurrency);
        
        // Convert to USD, then to target currency
        return amount / fromRate * toRate;
    }
    
    /**
     * Update an exchange rate
     * 
     * @param currency Currency to update
     * @param newRate New exchange rate relative to USD
     * @throws IllegalArgumentException if currency is not supported
     */
    public void updateExchangeRate(String currency, double newRate) {
        if (!exchangeRates.containsKey(currency)) {
            throw new IllegalArgumentException("Unsupported currency: " + currency);
        }
        
        exchangeRates.put(currency, newRate);
    }
    
    /**
     * Get all supported currencies
     * 
     * @return Array of supported currencies
     */
    public String[] getSupportedCurrencies() {
        return exchangeRates.keySet().toArray(new String[0]);
    }
    
    /**
     * Get exchange rate for a specific currency
     * 
     * @param currency Currency to get rate for
     * @return Exchange rate relative to USD
     * @throws IllegalArgumentException if currency is not supported
     */
    public double getExchangeRate(String currency) {
        if (!exchangeRates.containsKey(currency)) {
            throw new IllegalArgumentException("Unsupported currency: " + currency);
        }
        
        return exchangeRates.get(currency);
    }
}
