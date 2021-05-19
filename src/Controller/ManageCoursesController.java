package Controller;

import Functionality.SceneLoader;
import Functionality.User;
import Model.ConnectionStorage;
import Model.CourseStorage;
import Model.DBConnection;
import Model.UserStorage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManageCoursesController implements Initializable {

    DBConnection database = ConnectionStorage.getInstance().getConnection();
    @FXML
    Button editCourseButton;
    @FXML
    Button addNewCourseButton;
    @FXML
    Button homeButton;
    @FXML
    ListView<String> courseList;

    UserStorage userStorage = UserStorage.getInstance();
    User user = userStorage.currentUser();

    ArrayList<String> courses;

    public ManageCoursesController() throws SQLException {}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            String username = user.getUsername();
            courses = database.getCurrentUsersCourseNames(database.getCurrentUsersCourseIDs(username));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        addCourses();
    }

    public void addCourses() {
        for (String course : courses) {
            courseList.getItems().add(course);
        }
    }

    public void onEditCourse() {
        try {
            String selectedItem = courseList.getSelectionModel().getSelectedItem();
            CourseStorage.getInstance().setCourseName(selectedItem);
            if (courses.contains(selectedItem)) {
                SceneLoader.getInstance().loadEditScene(selectedItem, editCourseButton);
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void onAddNewCourse() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/AddCourse.fxml"));
            Stage stage = (Stage) addNewCourseButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add("View/Style.css");
            AddCourseController addCourseController = loader.getController();
            addCourseController.showElements(false);

            stage.setScene(scene);
            stage.show();
        } catch (IOException ignored) {}
    }

    public void onHomeClick() throws IOException {
        SceneLoader.getInstance().loadMainMenu(homeButton);
    }
}
