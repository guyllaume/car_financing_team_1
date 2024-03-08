package DAO;

import config.PostgresSQLConfig;
import model.Client;
import model.FinancingForm;
import model.Investor;
import model.User;

import java.sql.*;

public class FinancingFormDAO {
    public FinancingFormDAO() {}
    public void addFinancingForm(FinancingForm financingForm) {

        String SQL_INSERT = "INSERT INTO financingForm (marque, modele, annee, km, VIN, montantPret, dureePret, userId) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = PostgresSQLConfig.connect();
             PreparedStatement statement = conn.prepareStatement(SQL_INSERT)) {

            statement.setString(1, financingForm.getMarque());
            statement.setString(2, financingForm.getModele());
            statement.setInt(3, financingForm.getAnnee());
            statement.setInt(4, financingForm.getKm());
            statement.setString(5, financingForm.getVIN());
            statement.setDouble(6, financingForm.getMontantPret());
            statement.setInt(7, financingForm.getDureePret());
            statement.setInt(8, financingForm.getUserId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("The FinancingForm was created successfully!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public FinancingForm getFinancingFormByUserID(int userID){
            String SQL_SELECT = "SELECT * FROM financingform WHERE userid = ?;";

            try (Connection conn = PostgresSQLConfig.connect();
                 PreparedStatement statement = conn.prepareStatement(SQL_SELECT)) {

                statement.setInt(1, userID);
                ResultSet resultSet = statement.executeQuery();

                if(resultSet.next()) {
                    FinancingForm financingForm = new FinancingForm();
                    financingForm.setUserId(userID);
                    financingForm.setMarque(resultSet.getString("marque"));
                    financingForm.setModele(resultSet.getString("modele"));
                    financingForm.setAnnee(resultSet.getInt("annee"));
                    financingForm.setKm(resultSet.getInt("km"));
                    financingForm.setVIN(resultSet.getString("VIN"));
                    financingForm.setMontantPret(resultSet.getDouble("montantPret"));
                    financingForm.setDureePret(resultSet.getInt("dureePret"));
                    return financingForm;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return null;
    }
}
