package Model;

import java.sql.SQLException;
import java.util.ArrayList;

public class CourseStorage {
    private static CourseStorage courseStorage;

    DBConnection database = new ConnectionStorage().getConnection();
    private ArrayList<String> userNames = new ArrayList<>();
    private String courseName;
    private String subjectName;

    private CourseStorage() throws SQLException {}

    public static CourseStorage getInstance() throws SQLException {
        if (courseStorage == null){
            courseStorage = new CourseStorage();
        }
        return courseStorage;
    }

    public ArrayList<String> getUserNames() {
        return userNames;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getCourseName() {
        return courseName;
    }
}
