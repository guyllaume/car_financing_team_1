package service;

import DAO.UserDAO;
import DAO.UserDAOImpl;
import model.User;

public class UserService {
    private UserDAOImpl userDAO;

    public UserService(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    public boolean authenticateUser(String email, String password) {
        User user = userDAO.getUserByEmail(email);
        if (user != null) {
            // Perform password validation logic
            return validatePassword(password, user);
        }
        return false;
    }

    private boolean validatePassword(String password, User user) {
        // Implement password validation logic here (e.g., hashing, comparison)
        // Return true if password is valid, false otherwise
        return false;
    }
}
