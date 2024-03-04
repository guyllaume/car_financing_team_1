package DAO;

import config.PostgresSQLConfig;
import model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
        //No Use as it will be done through Client
    }

    public List<Person> getAllPersons() {
        String SQL_SELECT = "SELECT * FROM person;";
        List<Person> Persons = new ArrayList<>();

        try (Connection conn = PostgresSQLConfig.connect();
             PreparedStatement statement = conn.prepareStatement(SQL_SELECT)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");

                Person person = new Person(name, age);
                Persons.add(person);
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Persons;
    }
}
