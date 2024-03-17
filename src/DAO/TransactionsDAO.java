package DAO;

import config.PostgresSQLConfig;
import model.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionsDAO {
    public TransactionsDAO(){}

    public List<Transaction> getAllTransactions(int investorAccountId){
        String SQL_SELECT = "SELECT * FROM transactions WHERE compteinvestorid = ?;";

        try (Connection conn = PostgresSQLConfig.connect();
             PreparedStatement statement = conn.prepareStatement(SQL_SELECT)) {

            statement.setInt(1, investorAccountId);
            ResultSet resultSet = statement.executeQuery();

            List<Transaction> allTransactions = new ArrayList<>();
            while(resultSet.next()) {
                allTransactions.add(new Transaction());
                allTransactions.get(allTransactions.size()-1).setId(resultSet.getInt("id"));
                allTransactions.get(allTransactions.size()-1).setType(resultSet.getString("type"));
                allTransactions.get(allTransactions.size()-1).setTimeOfTransaction(resultSet.getDate("dateTransactions").toLocalDate());
                allTransactions.get(allTransactions.size()-1).setMontant(resultSet.getDouble("montant"));
                allTransactions.get(allTransactions.size()-1).setCompteInvestorId(resultSet.getInt("compteInvestorId"));
            }
            return allTransactions;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Transaction> getDepositTransactions(int investorAccountId){
        String SQL_SELECT = "SELECT * FROM transactions WHERE compteinvestorid = ? AND type = 'Investi';";

        try (Connection conn = PostgresSQLConfig.connect();
             PreparedStatement statement = conn.prepareStatement(SQL_SELECT)) {

            statement.setInt(1, investorAccountId);
            ResultSet resultSet = statement.executeQuery();

            List<Transaction> allTransactions = new ArrayList<>();
            while(resultSet.next()) {
                allTransactions.add(new Transaction());
                allTransactions.get(allTransactions.size()-1).setId(resultSet.getInt("id"));
                allTransactions.get(allTransactions.size()-1).setType(resultSet.getString("type"));
                allTransactions.get(allTransactions.size()-1).setTimeOfTransaction(resultSet.getDate("dateTransactions").toLocalDate());
                allTransactions.get(allTransactions.size()-1).setMontant(resultSet.getDouble("montant"));
                allTransactions.get(allTransactions.size()-1).setCompteInvestorId(resultSet.getInt("compteInvestorId"));
            }
            return allTransactions;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addTransactions(Transaction transaction){
        String SQL_INSERT = "INSERT INTO transactions (type, dateTransactions, montant, compteInvestorId) VALUES (?, ?, ?, ?)";

        try (Connection conn = PostgresSQLConfig.connect();
             PreparedStatement statement = conn.prepareStatement(SQL_INSERT)) {

            statement.setString(1, transaction.getType());
            statement.setDate(2, Date.valueOf(transaction.getTimeOfTransaction()));
            statement.setDouble(3, transaction.getMontant());
            statement.setInt(4, transaction.getCompteInvestorId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("The Transaction was created successfully!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
