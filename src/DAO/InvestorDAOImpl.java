package DAO;

import config.PostgresSQLConfig;
import model.Investor;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvestorDAOImpl {

    public void addInvestor(Investor investor) {
        String SQL_INSERT = "INSERT INTO investor (nomComplet, email, password, telephone) VALUES (?, ?, ?, ?)";

        try (Connection conn = PostgresSQLConfig.connect();
             PreparedStatement statement = conn.prepareStatement(SQL_INSERT)) {

            statement.setString(1, investor.getNomComplet());
            statement.setString(2, investor.getEmail());
            statement.setString(3, investor.getPassword());
            statement.setString(4, investor.getTelephone());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("A user was inserted successfully!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteInvestor(Investor investor) {
        String SQL_SELECT = "SELECT id FROM investor WHERE id = ?";
    }

    public List<Investor> getAllInvestors() {

        /*"nomBanque VARCHAR(255)," +
                "detailBancaire VARCHAR(255)," +
                "risque VARCHAR(50)," +
                "education VARCHAR(50)" ;*/
        String SQL_SELECT = "SELECT * FROM user WHERE ;";
        List<Investor> investors = new ArrayList<>();

        try (Connection conn = PostgresSQLConfig.connect();
             PreparedStatement statement = conn.prepareStatement(SQL_SELECT)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nomBanque = resultSet.getString("nomBanque");
                String detailBancaire = resultSet.getString("detailBancaire");
                String risque = resultSet.getString("risque");
                String education = resultSet.getString("education");

                /*Investor investor = new Investor(nomBanque, detailBancaire, risque, education);
                investor.setId(id);

                investors.add(investor);*/
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return investors;
    }
}
