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
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class ManageCoursesController implements Initializable {

    DBConnection database = ConnectionStorage.getInstance().getConnection();
    @FXML
    Button removeButton;
    @FXML
    Button addButton;
    @FXML
    Button homeButton;
    @FXML
    Text text;
    @FXML
    ListView<String> listView;

    UserStorage userStorage = UserStorage.getInstance();
    User user = userStorage.currentUser();

    ArrayList<String> courses;
    ArrayList<String> usernames;

    public ManageCoursesController() throws SQLException {}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            String username = user.getUsername();
            courses = database.getCurrentUsersCourseNames(database.getCurrentUsersCourseIDs(username));
            Collections.sort(courses);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        showCourses();
        text.setText("Choose a course to edit");
    }

    public void showCourses() {
        listView.getItems().clear();
        addButton.setText("Add new course");
        removeButton.setText("Remove selected course");
        for (String course : courses) {
            listView.getItems().add(course);
        }
    }

    public void onRemove() throws SQLException {
        String selectedItem = listView.getSelectionModel().getSelectedItem();
        if (courses.contains(selectedItem)) {
            database.removeCourse(database.getIDForSelectedCourse(selectedItem, user.getUsername()));
            listView.getItems().remove(selectedItem);
        } else if (usernames.contains(selectedItem)) {
            database.removeUser(database.getIDForSelectedCourse(CourseStorage.getInstance().getCourseName(), user.getUsername()), user.getUsername());
            listView.getItems().remove(selectedItem);
        }
    }

    public void onMouseClick(MouseEvent click) throws SQLException {
        String selectedItem = listView.getSelectionModel().getSelectedItem();
        try {
            if (click.getClickCount() == 2 && courses.contains(selectedItem)) {
                addButton.setText("Manage topics");
                CourseStorage.getInstance().setCourseName(selectedItem);
                int courseID = database.getIDForSelectedCourse(selectedItem, user.getUsername());
                usernames = database.getUsernamesForCourse(courseID);
                setText(selectedItem, courseID, 2);
                addUsernames();
            } else if (click.getClickCount() == 2 && selectedItem.equals("Go back")) {
                String courseName = CourseStorage.getInstance().getCourseName();
                setText(courseName, database.getIDForSelectedCourse(courseName, user.getUsername()), 1);
                showCourses();
            }
        } catch (NullPointerException ignored) {}
    }

    public void setText(String courseName, int courseID, int i) {
        if (i == 1) {
            text.setText("Choose a course to edit");
        } else if (i == 2) {
            text.setText("Users in " + courseName + " #" + courseID);
        }
    }

    public void addUsernames() {
        listView.getItems().clear();
        removeButton.setText("Remove selected user");
        for (String username : usernames) {
            listView.getItems().add(username);
        }
        listView.getItems().add("Go back");
    }

    public void onAdd() throws IOException, SQLException {
        if (addButton.getText().equals("Add new course")) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/AddCourse.fxml"));
                Stage stage = (Stage) addButton.getScene().getWindow();
                Scene scene = new Scene(loader.load());
                scene.getStylesheets().add("View/Style.css");

                stage.setScene(scene);
                stage.show();
            } catch (IOException ignored) {}
        } else if (addButton.getText().equals("Manage topics")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/ManageTopics.fxml"));
            Stage stage = (Stage) addButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add("View/Style.css");

            stage.setScene(scene);
            stage.show();
        }
    }

    public void onHomeClick() throws IOException {
        SceneLoader.getInstance().loadMainMenu(homeButton);
    }
}
