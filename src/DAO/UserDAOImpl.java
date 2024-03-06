package DAO;

import config.PostgresSQLConfig;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO{
    public User getUserByEmail(String email){
        String SQL_SELECT = "SELECT * FROM users WHERE email = ?";
        User user = new User();
        try (Connection conn = PostgresSQLConfig.connect();
             PreparedStatement statement = conn.prepareStatement(SQL_SELECT)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int clientId = resultSet.getInt("idClient");
                int investorId = resultSet.getInt("idInvestor");
                String nomComplet = resultSet.getString("nomComplet");
                String resultEmail = resultSet.getString("email");
                String password = resultSet.getString("password");
                String telephone = resultSet.getString("telephone");

                user = new User(nomComplet, resultEmail, password, telephone);
                user.setId(id);
                user.setClientId(clientId);
                user.setInvestorId(investorId);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    public void addUser(User user) {
        //Will be done through Client or Investor
        /*String SQL_INSERT = "INSERT INTO user (nomComplet, email, password, telephone) VALUES (?, ?, ?, ?)";

        try (Connection conn = PostgresSQLConfig.connect();
             PreparedStatement statement = conn.prepareStatement(SQL_INSERT)) {

            statement.setString(1, user.getNomComplet());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getTelephone());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("A user was inserted successfully!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    public void deleteUser(User user) {
        //Will be done through CLient or Investor since ON DELETE CASCADE
    }

    //No use really
    public List<User> getAllUsers() {
        String SQL_SELECT = "SELECT * FROM user;";
        List<User> users = new ArrayList<>();

        try (Connection conn = PostgresSQLConfig.connect();
             PreparedStatement statement = conn.prepareStatement(SQL_SELECT)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nomComplet = resultSet.getString("nomComplet");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String telephone = resultSet.getString("telephone");

                User user = new User(nomComplet, email, password, telephone);
                user.setId(id);

                users.add(user);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}
