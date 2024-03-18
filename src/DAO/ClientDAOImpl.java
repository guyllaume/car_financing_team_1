package DAO;

import config.PostgresSQLConfig;
import model.Client;
import model.User;

import java.sql.*;
import java.time.LocalDate;

public class ClientDAOImpl extends UserDAOImpl{


    public ClientDAOImpl(){
        super();
    }
    public void addClient(Client client) {
        String SQL_INSERT = "INSERT INTO client (infoEmploi, revenuAnnuel, credit, dateNaissance,statutMarital, anneeCanada) VALUES (?, ?, ?, ?, ?, ?)";
        String SQL_INSERT2 = "INSERT INTO users (idClient, nomComplet, email, password, salt, telephone) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = PostgresSQLConfig.connect();
             PreparedStatement statement = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, client.getInfoEmploi());
            statement.setDouble(2, client.getRevenuAnnuel());
            statement.setInt(3, client.getCredit());
            statement.setDate(4, Date.valueOf(client.getDateNaissance()));
            statement.setString(5, client.getStatutMarital());
            statement.setInt(6, client.getDateCanada());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                // Retrieve the generated keys
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    int generatedId = rs.getInt(1);
                    System.out.println("A Client was inserted successfully with ID: " + generatedId);

                    PreparedStatement statement1 = conn.prepareStatement(SQL_INSERT2);
                    statement1.setInt(1, generatedId);
                    statement1.setString(2, client.getNomComplet());
                    statement1.setString(3, client.getEmail());
                    statement1.setString(4, client.getPassword());
                    statement1.setBytes(5, client.getSalt());
                    statement1.setString(6, client.getTelephone());

                    affectedRows = statement1.executeUpdate();

                    if (affectedRows > 0) {
                        System.out.println("The User associated with the client was created successfully!");
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    //Inutile
    public Client getClientByEmail(String email){
        User user = super.getUserByEmail(email);
        if (user.getClientId() != 0){
            Client client;
            String SQL_SELECT = "SELECT * FROM client WHERE id = ?;";

            try (Connection conn = PostgresSQLConfig.connect();
                 PreparedStatement statement = conn.prepareStatement(SQL_SELECT)) {

                statement.setInt(1, user.getClientId());
                ResultSet resultSet = statement.executeQuery();

                if(resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String infoEmploi = resultSet.getString("infoEmploi");
                    double revenuAnnuel = resultSet.getDouble("revenuAnnuel");
                    int credit = resultSet.getInt("credit");
                    LocalDate dateNaissance = resultSet.getDate("dateNaissance").toLocalDate();
                    String statutMarital = resultSet.getString("statutMarital");
                    int dateCanada = resultSet.getInt("anneeCanada");

                    client = new Client(user.getNomComplet(), user.getEmail(), user.getPassword(), user.getTelephone(), infoEmploi, revenuAnnuel, credit, dateNaissance, statutMarital, dateCanada);
                    client.setId(user.getId());
                    client.setClientId(id);
                    client.setSalt(user.getSalt());
                    client.setPassword(user.getPassword());
                    return client;
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return null;
    }
}
