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
                "nbInstitution INT," +
                "nbTransit INT," +
                "nbCompte INT," +
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
        String createTableCompteInvestor = "CREATE TABLE IF NOT EXISTS compteInvestor (" +
                "id SERIAL PRIMARY KEY," +
                "solde DOUBLE PRECISION," +
                "userId INT," +
                "CONSTRAINT fk_User_id " +
                "FOREIGN KEY (userId) " +
                "REFERENCES users(id)" +
                ");";
        String createTableTransactions = "CREATE TABLE IF NOT EXISTS transactions (" +
                "id SERIAL PRIMARY KEY," +
                "type VARCHAR(20)," +
                "dateTransactions DATE," +
                "montant DOUBLE PRECISION," +
                "compteInvestorId INT," +
                "CONSTRAINT fk_compteInvestor_id " +
                "FOREIGN KEY (compteInvestorId) " +
                "REFERENCES compteInvestor(id)" +
                ");";
        String createFunctionToCalculateSolde = "CREATE OR REPLACE FUNCTION updateSolde() " +
                "RETURNS TRIGGER AS $$ " +
                "DECLARE " +
                "totalMontantDeposer DOUBLE PRECISION;" +
                "totalMontantRetirer DOUBLE PRECISION;" +
                "BEGIN " +
                "SELECT SUM(montant) INTO totalMontantDeposer " +
                "FROM transactions " +
                "WHERE compteInvestorId = NEW.compteInvestorId AND type = 'deposit';" +
                "SELECT SUM(montant) INTO totalMontantRetirer " +
                "FROM transactions " +
                "WHERE compteInvestorId = NEW.compteInvestorId AND type = 'withdrawl';" +
                "UPDATE compteInvestor " +
                "SET solde = COALESCE(totalMontantDeposer, 0) - COALESCE(totalMontantRetirer, 0) " +
                "WHERE id = NEW.compteInvestorId;" +
                "RETURN NULL;" +
                "END; " +
                "$$ LANGUAGE plpgsql;";
        String dropTrigger = "DROP TRIGGER IF EXISTS triggerUpdateSolde ON transactions;";
        String createTriggerTransactions = "CREATE TRIGGER triggerUpdateSolde " +
                "AFTER INSERT OR UPDATE ON transactions " +
                "FOR EACH ROW " +
                "EXECUTE FUNCTION updateSolde();";


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
            statement.execute(createTableCompteInvestor);
            System.out.println("Table 'compteInvestor' created or already exists.");
            statement.execute(createTableTransactions);
            System.out.println("Table 'transactions' created or already exists.");
            statement.execute(createFunctionToCalculateSolde);
            System.out.println("Function 'updateSolde' created or replaced succesfully");
            statement.execute(dropTrigger);
            statement.execute(createTriggerTransactions);
            System.out.println("Trigger 'triggerUpdateSolde' created or already exists.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
