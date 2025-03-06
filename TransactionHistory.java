import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionHistory {
    private static final String URL = "jdbc:mysql://localhost:3306/currency_db";
    private static final String USER = "root";
    private static final String PASSWORD = "password";
    
    public static void saveTransaction(String fromCurrency, String toCurrency, double amount, double convertedAmount) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "INSERT INTO transactions (from_currency, to_currency, amount, converted_amount) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, fromCurrency);
            pstmt.setString(2, toCurrency);
            pstmt.setDouble(3, amount);
            pstmt.setDouble(4, convertedAmount);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static List<String> getTransactionHistory() {
        List<String> history = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "SELECT * FROM transactions";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                history.add(rs.getString("from_currency") + " to " + rs.getString("to_currency") + ": " + rs.getDouble("amount") + " -> " + rs.getDouble("converted_amount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return history;
    }
}