package Model;

import java.sql.SQLException;
import java.util.ArrayList;

public class CourseStorage {
    private static CourseStorage courseStorage;

    static {
        try {
            courseStorage = new CourseStorage();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    DBConnection database = new ConnectionStorage().getConnection();
    private ArrayList<String> userNames = new ArrayList<>();
    private String courseName;

    public CourseStorage() throws SQLException {}

    public static CourseStorage getInstance() {
        return courseStorage;
    }

    public ArrayList<String> getUserNames() {
        return userNames;
    }

    public void setCourseName(String course) {
        this.courseName = courseName;
    }

    public String getCourse() {
        return courseName;
    }
}
