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
import java.util.ResourceBundle;

public class EditCourseController implements Initializable {

    DBConnection database = ConnectionStorage.getInstance().getConnection();
    @FXML
    Button addNewTopic;
    @FXML
    Button removeStudentButton;
    @FXML
    Button homeButton;
    @FXML
    Text textField;
    @FXML
    ListView<String> listView;

    ArrayList<String> usernames;

    UserStorage userStorage = UserStorage.getInstance();
    User user = userStorage.currentUser();

    public EditCourseController() throws SQLException {}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setText(String course, int courseID) throws SQLException {
        usernames = database.getUsernamesForCourse(courseID);
        for (String username : usernames) {
            listView.getItems().add(username);
        }
        listView.getItems().add("Go back");
        if (courseID != 0) {
            textField.setText(course + " #" + courseID);
        } else {
            textField.setText((course));
        }
    }

    public void onAddNewTopic() throws SQLException {
        listView.getItems().clear();
        for (String subject : database.getSubjects()) {
            listView.getItems().add(subject);
        }
        listView.getItems().add("Go back");
    }

    public void onMouseClick(MouseEvent click) throws SQLException {
        String selectedItem = listView.getSelectionModel().getSelectedItem();
        if (click.getClickCount() == 2 && selectedItem.equals("Go back")) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/ManageCourses.fxml"));
                Stage stage = (Stage) listView.getScene().getWindow();
                Scene scene = new Scene(loader.load());
                scene.getStylesheets().add("View/Style.css");

                stage.setScene(scene);
                stage.show();
            } catch (IOException ignored) {}
        } else if (click.getClickCount() == 2 && database.getSubjects().contains(selectedItem)) {
            listView.getItems().clear();
            for (String topic : database.getTopics(selectedItem)) {
                listView.getItems().add(topic);
            }
            listView.getItems().add("Go back");
        } else {
            for (String subject : database.getSubjects()) {
                if (click.getClickCount() == 2 && database.getTopics(subject).contains(selectedItem)) {
                    int courseID = database.getIDForSelectedCourse(CourseStorage.getInstance().getCourseName(), user.getUsername());
                    database.addTopicToCourse(courseID, selectedItem);
                    listView.getItems().remove(selectedItem);
                }
            }
        }
    }

    public void onRemoveStudent() throws SQLException {
        String selectedItem = listView.getSelectionModel().getSelectedItem();
        int courseID = database.getIDForSelectedCourse(CourseStorage.getInstance().getCourseName(), user.getUsername());
        if (usernames.contains(selectedItem)) {
            database.removeUser(courseID, selectedItem);
            listView.getItems().remove(selectedItem);
        }
    }

    public void onHomeClick() throws IOException {
        SceneLoader.getInstance().loadMainMenu(homeButton);
    }
}
