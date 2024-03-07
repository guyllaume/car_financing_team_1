package DAO;

import model.User;

import java.util.List;
public interface UserDAO {
    void addUser(User user);
    void deleteUser(User user);
    List<User> getAllUsers();
}
