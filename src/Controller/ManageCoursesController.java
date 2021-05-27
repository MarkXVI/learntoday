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
            showCourses();
            text.setText("Choose a course to edit");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void showCourses() throws SQLException {
        courses = database.getCurrentUsersCourseNames(user.getUsername());
        Collections.sort(courses);
        listView.getItems().clear();
        addButton.setText("Add new course");
        removeButton.setText("Remove selected course");
        for (String course : courses) {
            listView.getItems().add(course);
        }
    }

    public void onRemove(){
        try{
        String selectedItem = listView.getSelectionModel().getSelectedItem();
        if (courses.contains(selectedItem)) {
            int courseID = database.getIDForSelectedCourse(selectedItem, user.getUsername());
            database.removeCourse(courseID);
            listView.getItems().remove(selectedItem);
        } else if (usernames.contains(selectedItem)) {
            int courseID = database.getIDForSelectedCourse(CourseStorage.getInstance().getCourseName(), user.getUsername());
            database.removeUser(courseID, selectedItem);
            listView.getItems().remove(selectedItem);
            if (selectedItem.equals(user.getUsername())){
                onGoBack();
                showCourses();
            }
        }}catch(Exception ignored){}
    }

    public void onMouseClick(MouseEvent click) throws SQLException {
        String selectedItem = listView.getSelectionModel().getSelectedItem();
        try {
            if (click.getClickCount() == 2 && courses.contains(selectedItem)) {
                addButton.setText("Manage topics");
                CourseStorage.getInstance().setCourseName(selectedItem);
                int courseID = database.getIDForSelectedCourse(selectedItem, user.getUsername());
                usernames = database.getUsernamesForCourse(courseID);
                setText(selectedItem, courseID);
                addUsernames();
            } else if (click.getClickCount() == 2 && selectedItem.equals("Go back")) {
                onGoBack();
                showCourses();
            }
        } catch (NullPointerException ignored) {}
    }

    public void onGoBack(){
        text.setText("Choose a course to edit");
    }

    public void setText(String courseName, int courseID) {
        text.setText("Users in " + courseName + " #" + courseID);
    }

    public void addUsernames() {
        listView.getItems().clear();
        removeButton.setText("Remove selected user");
        for (String username : usernames) {
            listView.getItems().add(username);
        }
        listView.getItems().add("Go back");
    }

    public void onAdd() throws IOException {
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
