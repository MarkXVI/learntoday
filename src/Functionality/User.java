package Functionality;

public class User {
    private String username;
    private String firstname;
    private String lastname;
    private String teacher;

    public User(String firstname, String lastname, String username, String teacher){
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.teacher = teacher;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
