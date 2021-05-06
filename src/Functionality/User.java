package Functionality;

public class User {
    private String username;
    private String firstname;
    private String lastname;
    private int teacher; // Teacher is a TinyInt in SQL which is a 1 or a 0. Corresponding to true or false.

    public User(String username, String firstName, String lastName, int teacher) {
        this.username = username;
        this.firstname = firstName;
        this.lastname = lastName;
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

    public int getTeacher() {
        return teacher;
    }

    public void setTeacher(int teacher) {
        this.teacher = teacher;
    }
}
