package DAO;

import config.PostgresSQLConfig;
import model.Client;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ClientDAOImpl extends UserDAOImpl{


    public ClientDAOImpl(){
        super();
    }

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
                    return client;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
