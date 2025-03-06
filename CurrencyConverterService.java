public class CurrencyConverterService {
    public static double convert(String fromCurrency, String toCurrency, double amount) {
        double exchangeRate = ExchangeRateAPI.getExchangeRate(fromCurrency, toCurrency);
        if (exchangeRate > 0) {
            double convertedAmount = amount * exchangeRate;
            TransactionHistory.saveTransaction(fromCurrency, toCurrency, amount, convertedAmount);
            return convertedAmount;
        }
        return -1;
    }
}