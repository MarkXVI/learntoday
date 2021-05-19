package Controller;

import Functionality.SceneLoader;
import Functionality.User;
import Model.ConnectionStorage;
import Model.CourseStorage;
import Model.DBConnection;
import Model.UserStorage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManageTopicsController implements Initializable {

    DBConnection database = ConnectionStorage.getInstance().getConnection();
    @FXML
    Button showButton;
    @FXML
    Button rightButton;
    @FXML
    Button homeButton;
    @FXML
    Text textField;
    @FXML
    ListView<String> listView;

    UserStorage userStorage = UserStorage.getInstance();
    User user = userStorage.currentUser();

    ArrayList<String> usernames;
    ArrayList<String> existingTopics;
    ArrayList<String> subjects;
    ArrayList<String> topics;

    int courseID;
    String courseName;

    public ManageTopicsController() throws SQLException {}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        courseName = CourseStorage.getInstance().getCourseName();
        try {
            courseID = database.getIDForSelectedCourse(courseName, user.getUsername());
            subjects = database.getSubjects();
            existingTopics = database.getTopicsForSelectedCourse(courseID);
            usernames = database.getUsernamesForCourse(courseID);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        onShow();
    }

    public void onRightButton() throws SQLException {
        String selectedItem = listView.getSelectionModel().getSelectedItem();
        topics = database.getTopicsForSelectedSubject(CourseStorage.getInstance().getSubjectName());
        if (topics.contains(selectedItem)) {
            database.addTopicToCourse(database.getIDForSelectedCourse(CourseStorage.getInstance().getCourseName(), user.getUsername()), selectedItem);
            existingTopics.add(selectedItem);
            listView.getItems().remove(selectedItem);
        }
    }

    public void onMouseClick(MouseEvent click) throws SQLException {
        String selectedItem = listView.getSelectionModel().getSelectedItem();
        topics = database.getTopicsForSelectedSubject(selectedItem);
        try {
            if (click.getClickCount() == 2 && subjects.contains(selectedItem)) {
                CourseStorage.getInstance().setSubjectName(selectedItem);
                showTopics();
            } else if (click.getClickCount() == 2 && selectedItem.equals("Go back")) {
                showSubjects();
            }
        } catch (NullPointerException ignored) {}
    }

    public void showTopics() {
        listView.getItems().clear();
        for (String topic : topics) {
            if (!existingTopics.contains(topic)) {
                listView.getItems().add(topic);
            }
        }
        listView.getItems().add("Go back");
    }

    public void showSubjects() {
        listView.getItems().clear();
        for (String subject : subjects) {
            listView.getItems().add(subject);
        }
    }

    public void onShow() {
        listView.getItems().clear();
        switch (showButton.getText()) {
            case "", "Show your topics" -> {
                textField.setText("Current topics in " + courseID + " #" + courseID);
                showButton.setText("Show topics to add");
                rightButton.setText("Remove selected topic");
                for (String topic : existingTopics) {
                    listView.getItems().add(topic);
                }
            }
            case "Show topics to add" -> {
                textField.setText("Choose a subject");
                showButton.setText("Show your topics");
                rightButton.setText("Add selected topic");
                for (String subject : subjects) {
                    if (!existingTopics.contains(subject))
                        listView.getItems().add(subject);
                }
            }
        }
    }

    public void onHomeClick() throws IOException {
        SceneLoader.getInstance().loadMainMenu(homeButton);
    }
}