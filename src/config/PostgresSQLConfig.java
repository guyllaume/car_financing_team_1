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
        /*String createTableSQL = "CREATE TABLE IF NOT EXISTS person (" +
                "id SERIAL PRIMARY KEY," +
                "name VARCHAR(255)," +
                "age INT" +
                ");";
                no need for a person table*/
        String createTableClient = "CREATE TABLE IF NOT EXISTS client (" +
                "id SERIAL PRIMARY KEY," +
                "infoEmploi VARCHAR(255)," +
                "revenuAnnuel DOUBLE PRECISION," +
                "credit INT," +
                "dateNaissance DATE," +
                "statutMarital VARCHAR(30)," +
                "anneeCanada INT" +
                ");";
        String createTableInvestor = "CREATE TABLE IF NOT EXISTS investor (" +
                "id SERIAL PRIMARY KEY," +
                "nomBanque VARCHAR(255)," +
                "detailBancaire VARCHAR(255)," +
                "risque VARCHAR(50)," +
                "education VARCHAR(50)" +
                ");";
        String createTableUser = "CREATE TABLE IF NOT EXISTS users (" +
                "id SERIAL PRIMARY KEY," +
                "idClient INT," +
                "idInvestor INT," +
                "nomComplet VARCHAR(255)," +
                "email VARCHAR(255) UNIQUE," +
                "password VARCHAR(255)," +
                "salt BYTEA," +
                "telephone VARCHAR(15)," +
                "CONSTRAINT fk_Client_id " +
                "FOREIGN KEY (idClient)" +
                "REFERENCES client(id)" +
                "ON DELETE CASCADE," +
                "CONSTRAINT fk_Investor_id " +
                "FOREIGN KEY (idInvestor)" +
                "REFERENCES investor(id)" +
                "ON DELETE CASCADE," +
                "CONSTRAINT check_only_one_relation " +
                "CHECK (" +
                "(idClient IS NULL AND idInvestor IS NOT NULL) OR" +
                "(idClient IS NOT NULL AND idInvestor IS NULL)" +
                ")" +
                ");";
        String createTableFinancingForm = "CREATE TABLE IF NOT EXISTS financingForm (" +
                "id SERIAL PRIMARY KEY," +
                "marque VARCHAR(255)," +
                "modele VARCHAR(255)," +
                "annee INT," +
                "km INT," +
                "VIN VARCHAR(255)," +
                "montantPret DOUBLE PRECISION," +
                "dureePret INT," +
                "userId INT," +
                "CONSTRAINT fk_User_id " +
                "FOREIGN KEY (userId) " +
                "REFERENCES users(id)" +
                ");";

        try (Connection conn = connect();
             Statement statement = conn.createStatement()) {
            // Execute the SQL statement to create the table
            statement.execute(createTableClient);
            System.out.println("Table 'client' created or already exists.");
            statement.execute(createTableInvestor);
            System.out.println("Table 'investor' created or already exists.");
            statement.execute(createTableUser);
            System.out.println("Table 'user' created or already exists.");
            statement.execute(createTableFinancingForm);
            System.out.println("Table 'financingForm' created or already exists.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
