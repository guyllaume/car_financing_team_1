package DAO;

import config.PostgresSQLConfig;
import model.Investor;
import model.User;

import java.sql.*;
import java.util.List;

public class InvestorDAOImpl extends UserDAOImpl {

    public InvestorDAOImpl(){
        super();
    }

    public void addInvestor(Investor investor) {
        String SQL_INSERT = "INSERT INTO investor (nomBanque, nbInstitution, nbTransit, nbCompte, risque, education) VALUES (?, ?, ?, ?, ?, ?)";
        String SQL_INSERT2 = "INSERT INTO users (idInvestor, nomComplet, email, password, salt, telephone) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = PostgresSQLConfig.connect();
             PreparedStatement statement = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, investor.getNomBanque());
            statement.setInt(2, investor.getNbInstitution());
            statement.setInt(3, investor.getNbTransit());
            statement.setInt(4, investor.getNbCompte());
            statement.setString(5, investor.getRisque());
            statement.setString(6, investor.getEducation());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                // Retrieve the generated keys
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    int generatedId = rs.getInt(1);
                    System.out.println("An Investor was inserted successfully with ID: " + generatedId);

                    PreparedStatement statement1 = conn.prepareStatement(SQL_INSERT2);
                    statement1.setInt(1, generatedId);
                    statement1.setString(2, investor.getNomComplet());
                    statement1.setString(3, investor.getEmail());
                    statement1.setString(4, investor.getPassword());
                    statement1.setBytes(5, investor.getSalt());
                    statement1.setString(6, investor.getTelephone());

                    affectedRows = statement1.executeUpdate();

                    if (affectedRows > 0) {
                        System.out.println("The User associated with the investor was created successfully!");
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteInvestor(Investor investor) {
        String SQL_DELETE = "DELETE FROM investor WHERE id = ?";
        try (Connection conn = PostgresSQLConfig.connect();
             PreparedStatement statement = conn.prepareStatement(SQL_DELETE)) {

            statement.setInt(1,investor.getId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 1) {
                System.out.println("The Investor was deleted successfully!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Inutile
    public Investor getInvestorByEmail(String email){
        User user = super.getUserByEmail(email);
        if (user.getInvestorId() != 0){
            Investor investor;
            String SQL_SELECT = "SELECT * FROM investor WHERE id = ?;";

            try (Connection conn = PostgresSQLConfig.connect();
                 PreparedStatement statement = conn.prepareStatement(SQL_SELECT)) {

                statement.setInt(1, user.getInvestorId());
                ResultSet resultSet = statement.executeQuery();

                if(resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nomBanque = resultSet.getString("nomBanque");
                    int nbInstitution = resultSet.getInt("nbInstitution");
                    int nbTransit = resultSet.getInt("nbTransit");
                    int nbCompte = resultSet.getInt("nbCompte");
                    String risque = resultSet.getString("risque");
                    String education = resultSet.getString("education");

                    investor = new Investor(user.getNomComplet(), user.getEmail(), user.getPassword(), user.getTelephone(), nomBanque, nbInstitution, nbTransit, nbCompte, risque, education);
                    investor.setId(user.getId());
                    investor.setInvestorId(id);
                    investor.setSalt(user.getSalt());
                    investor.setPassword(user.getPassword());
                    return investor;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    //Inutile
    public List<Investor> getAllInvestors() {

        /*String SQL_SELECT = "SELECT * FROM user WHERE ;";
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

                Investor investor = new Investor(nomBanque, detailBancaire, risque, education);
                investor.setId(id);

                investors.add(investor);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return investors;*/
        return null;
    }
}
