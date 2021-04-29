package Functionality;

public class UserStorage {
    private static UserStorage storage;
    private User user;

    private UserStorage(String firstname, String lastname, String username, int teacher){
        user = new User(firstname, lastname, username, teacher);
    }

    public static UserStorage getInstance(String firstname, String lastname, String username, int teacher){
        if(storage == null){
            storage = new UserStorage(firstname, lastname, username, teacher);
        }
        return storage;
    }

    public User getUser(){
        return user;
    }
}
