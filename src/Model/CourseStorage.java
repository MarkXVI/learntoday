package Model;

public class CourseStorage {
    private static CourseStorage instance;

    private String courseName;
    private String subjectName;

    private CourseStorage(){}

    public static CourseStorage getInstance(){
        if (instance == null){
            instance = new CourseStorage();
        }
        return instance;
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
