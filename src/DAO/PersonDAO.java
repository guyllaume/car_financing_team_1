package DAO;

import model.Person;

import java.util.List;

public interface PersonDAO {
    void addPerson(Person person);
    void deletePerson(Person person);
    List<Person> getAllPersons();
}
