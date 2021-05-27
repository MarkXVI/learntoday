package Model;

import Functionality.User;

public class UserStorage {
    private static UserStorage instance;
    private User user;

    public static UserStorage getInstance() {
        if (instance == null){
            instance = new UserStorage();
        }
        return instance;
    }

    public void createUser(String username, String firstName, String lastName, int teacher){
        user = new User(username, firstName, lastName, teacher);
    }

    public User currentUser() {
        return user;
    }
}
