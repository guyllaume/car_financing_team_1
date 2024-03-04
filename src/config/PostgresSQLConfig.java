package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

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
        String createTableUser = "CREATE TABLE IF NOT EXISTS user (" +
                "id SERIAL PRIMARY KEY," +
                "email VARCHAR(255)," +
                "password VARCHAR(255)," +
                "telephone VARCHAR(15)" +
                ");";
        String createTableClient = "CREATE TABLE IF NOT EXISTS client (" +
                "id SERIAL PRIMARY KEY," +
                "idPerson INT," +
                "idUser INT," +
                "infoEmploi VARCHAR(255)," +
                "revenuAnnuel DOUBLE PRECISION," +
                "credit INT," +
                "statutMarital VARCHAR(30)," +
                "anneeCanada INT," +
                "CONSTRAINT fk_user_id" +
                "FOREIGN KEY (idUser)" +
                "REFERENCES user(id)" +
                "ON DELETE CASCADE," +
                "CONSTRAINT fk_person_id" +
                "FOREIGN KEY (idPerson)" +
                "REFERENCES person(id)" +
                "ON DELETE CASCADE" +
                ");";
        String createTableInvestor = "CREATE TABLE IF NOT EXISTS investor (" +
                "id SERIAL PRIMARY KEY," +
                "nomBanque VARCHAR(255)," +
                "detailBancaire VARCHAR(255)," +
                "risque VARCHAR(50)," +
                "education VARCHAR(50)" +
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
