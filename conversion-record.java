package currencyconverter;

import java.util.Date;

/**
 * Model class to represent a currency conversion record.
 * Used for storing and retrieving conversion data.
 * 
 * @author Rukma Rao (RA2211031010090)
 */
public class ConversionRecord {
    private int id;
    private double amount;
    private String fromCurrency;
    private String toCurrency;
    private double convertedAmount;
    private Date conversionDate;
    private String email;
    
    /**
     * Constructor with all fields
     */
    public ConversionRecord(int id, double amount, String fromCurrency, 
                           String toCurrency, double convertedAmount, 
                           Date conversionDate, String email) {
        this.id = id;
        this.amount = amount;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.convertedAmount = convertedAmount;
        this.conversionDate = conversionDate;
        this.email = email;
    }
    
    /**
     * Constructor for new records (without ID and date, will be generated)
     */
    public ConversionRecord(double amount, String fromCurrency, 
                          String toCurrency, double convertedAmount, String email) {
        this.amount = amount;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.convertedAmount = convertedAmount;
        this.email = email;
        this.conversionDate = new Date(); // Current date/time
    }
    
    // Getters and setters
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public String getFromCurrency() {
        return fromCurrency;
    }
    
    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }
    
    public String getToCurrency() {
        return toCurrency;
    }
    
    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }
    
    public double getConvertedAmount() {
        return convertedAmount;
    }
    
    public void setConvertedAmount(double convertedAmount) {
        this.convertedAmount = convertedAmount;
    }
    
    public Date getConversionDate() {
        return conversionDate;
    }
    
    public void setConversionDate(Date conversionDate) {
        this.conversionDate = conversionDate;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString() {
        return String.format(
            "Conversion[id=%d, %.2f %s to %s = %.2f, date=%s, email=%s]",
            id, amount, fromCurrency, toCurrency, convertedAmount, 
            conversionDate, email);
    }
}
