package DAO;

import config.PostgresSQLConfig;
import model.FinancingForm;
import model.Investor;

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
}
