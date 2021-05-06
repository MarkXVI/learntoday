package Model;

import Functionality.User;

public class UserStorage {
    private final static UserStorage instance = new UserStorage(); // This will make sure the UserStorage only can have 1 instance.

    public static UserStorage getInstance() {
        return instance;
    }

    private User user = new User();

    public User currentUser() {return user;}
}
