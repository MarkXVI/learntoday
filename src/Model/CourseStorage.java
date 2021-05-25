package Model;

import java.sql.SQLException;

public class CourseStorage {
    private static CourseStorage courseStorage;

    private String courseName;
    private String subjectName;

    private CourseStorage(){}

    public static CourseStorage getInstance() throws SQLException {
        if (courseStorage == null){
            courseStorage = new CourseStorage();
        }
        return courseStorage;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getCourseName() {
        return courseName;
    }
}
