package currencyconverter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Database manager for the Currency Converter application.
 * Handles database connections and initialization.
 * 
 * @author Rukma Rao (RA2211031010090)
 */
public class DatabaseManager {
    // Database connection properties
    private static final String DB_URL = "jdbc:sqlite:currencyconverter.db";
    
    /**
     * Constructor - initializes the database
     */
    public DatabaseManager() {
        // Initialize database and create tables if they don't exist
        initializeDatabase();
    }
    
    /**
     * Get a connection to the database
     * 
     * @return Connection object
     * @throws SQLException if a database error occurs
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
    
    /**
     * Initialize the database by creating required tables if they don't exist
     */
    private void initializeDatabase() {
        Connection conn = null;
        Statement stmt = null;
        
        try {
            // Register JDBC driver for SQLite
            Class.forName("org.sqlite.JDBC");
            
            // Open a connection
            conn = getConnection();
            System.out.println("Connected to database successfully");
            
            // Create conversions table if it doesn't exist
            stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS conversions (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "amount DOUBLE NOT NULL," +
                        "from_currency TEXT NOT NULL," +
                        "to_currency TEXT NOT NULL," +
                        "converted_amount DOUBLE NOT NULL," +
                        "conversion_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                        "email TEXT" +
                        ")";
            
            stmt.executeUpdate(sql);
            System.out.println("Table created or already exists");
            
        } catch (Exception e) {
            e.printStackTrace();
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
     * Add a new column to the conversions table
     * 
     * @param columnName Name of the column to add
     * @param columnType SQL type of the column
     * @return true if column added successfully, false otherwise
     */
    public boolean addColumn(String columnName, String columnType) {
        Connection conn = null;
        Statement stmt = null;
        
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            
            // SQL to add a new column
            String sql = "ALTER TABLE conversions ADD COLUMN " + columnName + " " + columnType;
            
            stmt.executeUpdate(sql);
            System.out.println("Column " + columnName + " added successfully");
            return true;
            
        } catch (SQLException e) {
            // Column might already exist
            System.out.println("Error adding column: " + e.getMessage());
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
