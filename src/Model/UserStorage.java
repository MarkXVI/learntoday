package Model;

import Functionality.User;

public class UserStorage {
    private final static UserStorage instance = new UserStorage();

    public static UserStorage getInstance() {
        return instance;
    }

    private User user = new User();

    public User currentUser() {
        return user;
    }
}
