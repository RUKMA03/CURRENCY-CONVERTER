package currencyconverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Data Access Object (DAO) for conversion records.
 * Handles database operations related to conversions.
 * 
 * @author Rukma Rao (RA2211031010090)
 */
public class ConversionDAO {
    private DatabaseManager dbManager;
    
    /**
     * Constructor
     */
    public ConversionDAO() {
        dbManager = new DatabaseManager();
    }
    
    /**
     * Save a conversion record to the database
     * 
     * @param record The conversion record to save
     * @return true if saving was successful, false otherwise
     */
    public boolean saveConversion(ConversionRecord record) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = dbManager.getConnection();
            String sql = "INSERT INTO conversions (amount, from_currency, to_currency, " +
                        "converted_amount, conversion_date, email) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";
            
            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, record.getAmount());
            stmt.setString(2, record.getFromCurrency());
            stmt.setString(3, record.getToCurrency());
            stmt.setDouble(4, record.getConvertedAmount());
            
            // Convert Java Date to SQL Timestamp
            stmt.setTimestamp(5, new Timestamp(record.getConversionDate().getTime()));
            stmt.setString(6, record.getEmail());
            
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
     * Get all conversion records from the database
     * 
     * @return List of all conversion records
     */
    public List<ConversionRecord> getAllConversions() {
        List<ConversionRecord> conversions = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = dbManager.getConnection();
            String sql = "SELECT id, amount, from_currency, to_currency, " +
                        "converted_amount, conversion_date, email " +
                        "FROM conversions ORDER BY conversion_date DESC";
            
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                ConversionRecord record = new ConversionRecord(
                    rs.getInt("id"),
                    rs.getDouble("amount"),
                    rs.getString("from_currency"),
                    rs.getString("to_currency"),
                    rs.getDouble("converted_amount"),
                    new Date(rs.getTimestamp("conversion_date").getTime()),
                    rs.getString("email")
                );
                
                conversions.add(record);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return conversions;
    }
    
    /**
     * Get conversion records by email
     * 
     * @param email Email to search for
     * @return List of conversion records matching the email
     */
    public List<ConversionRecord> getConversionsByEmail(String email) {
        List<ConversionRecord> conversions = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = dbManager.getConnection();
            String sql = "SELECT id, amount, from_currency, to_currency, " +
                        "converted_amount, conversion_date, email " +
                        "FROM conversions WHERE email = ? " +
                        "ORDER BY conversion_date DESC";
            
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                ConversionRecord record = new ConversionRecord(
                    rs.getInt("id"),
                    rs.getDouble("amount"),
                    rs.getString("from_currency"),
                    rs.getString("to_currency"),
                    rs.getDouble("converted_amount"),
                    new Date(rs.getTimestamp("conversion_date").getTime()),
                    rs.getString("email")
                );
                
                conversions.add(record);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return conversions;
    }
    
    /**
     * Delete a conversion record from the database
     * 
     * @param id ID of the record to delete
     * @return true if deletion was successful, false otherwise
     */
    public boolean deleteConversion(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = dbManager.getConnection();
            String sql = "DELETE FROM conversions WHERE id = ?";
            
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            
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
}
