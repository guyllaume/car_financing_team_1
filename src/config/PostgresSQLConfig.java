package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgresSQLConfig {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Method to initialize the database by creating necessary tables
    public static void initializeDatabase() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS person (" +
                "id SERIAL PRIMARY KEY," +
                "name VARCHAR(255)," +
                "age INT" +
                ");";

        try (Connection conn = connect();
             Statement statement = conn.createStatement()) {
            // Execute the SQL statement to create the table
            statement.execute(createTableSQL);
            System.out.println("Table 'persons' created or already exists.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
