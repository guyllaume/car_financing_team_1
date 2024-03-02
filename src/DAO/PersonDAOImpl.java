package DAO;

import config.PostgresSQLConfig;
import model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PersonDAOImpl implements PersonDAO {

    public void addPerson(Person person) {
        String SQL_INSERT = "INSERT INTO person (name, age) VALUES (?, ?)";

        try (Connection conn = PostgresSQLConfig.connect();
             PreparedStatement statement = conn.prepareStatement(SQL_INSERT)) {

            statement.setString(1, person.getName());
            statement.setInt(2, person.getAge());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("A person was inserted successfully!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePerson(Person person) {
        // Implementation
    }

    public List<Person> getAllPersons() {
        // Implementation
        return null;
    }
}
