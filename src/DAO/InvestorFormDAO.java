package DAO;

import config.PostgresSQLConfig;
import model.FinancingForm;
import model.InvestorForm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InvestorFormDAO {
    public InvestorFormDAO(){}

    public InvestorForm getInvestorAccount(int userId){
        String SQL_SELECT = "SELECT * FROM financingform WHERE userid = ?;";

        try (Connection conn = PostgresSQLConfig.connect();
             PreparedStatement statement = conn.prepareStatement(SQL_SELECT)) {

            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                InvestorForm investorForm = new InvestorForm();
                investorForm.setUserId(userId);
                investorForm.setSolde(resultSet.getDouble("solde"));
                investorForm.setId(resultSet.getInt("id"));
                return investorForm;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createInvestorAccount(int userId){
        String SQL_INSERT = "INSERT INTO compteinvestor (solde, userId) VALUES (?, ?)";

        try (Connection conn = PostgresSQLConfig.connect();
             PreparedStatement statement = conn.prepareStatement(SQL_INSERT)) {

            statement.setInt(1, 0);
            statement.setInt(2, userId);

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("The InvestorAccount was created successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
