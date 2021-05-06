package Model;

import Functionality.User;

public class UserStorage {
    private final static UserStorage instance = new UserStorage(); // This will make sure the UserStorage only can have 1 instance.
    private User user;

    public static UserStorage getInstance() {
        return instance;
    }

    public static void createUser(String username, String firstName, String lastName, int teacher){
        user = new User(username, firstName, lastName, teacher);
    }


    public User currentUser() {return user;}
}
