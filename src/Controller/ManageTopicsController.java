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
    ArrayList<String> subjects;
    ArrayList<String> topicsInSelectedCourse;
    ArrayList<String> topicsInSelectedSubject;
    ArrayList<String> allTopics;

    int courseID;
    String courseName;

    public ManageTopicsController() throws SQLException {}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            allTopics = database.getAllTopics();
            courseName = CourseStorage.getInstance().getCourseName();
            courseID = database.getIDForSelectedCourse(courseName, user.getUsername());
            subjects = database.getSubjects();
            usernames = database.getUsernamesForCourse(courseID);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        onShow();
    }

    public void onRightButton() {
        String selectedItem = listView.getSelectionModel().getSelectedItem();
        topicsInSelectedCourse = database.getTopicsForSelectedCourse(courseID);
        if (rightButton.getText().equals("Remove selected topic") && topicsInSelectedCourse.contains(selectedItem)) {
            topicsInSelectedCourse.remove(selectedItem);
            listView.getItems().remove(selectedItem);
            database.removeTopic(courseID, selectedItem);
        }
        else if (rightButton.getText().equals("Add selected topic") && allTopics.contains(selectedItem)) {
            topicsInSelectedCourse.add(selectedItem);
            listView.getItems().remove(selectedItem);
            database.addTopicToCourse(courseID, selectedItem);
        }
    }

    public void onMouseClick(MouseEvent click) throws SQLException {
        String selectedItem = listView.getSelectionModel().getSelectedItem();
        topicsInSelectedSubject = database.getTopicsForSelectedSubject(selectedItem);
        try {
            if (click.getClickCount() == 2 && subjects.contains(selectedItem)) {
                textField.setText("Choose a topic");
                CourseStorage.getInstance().setSubjectName(selectedItem);
                showTopics();
            } else if (click.getClickCount() == 2 && selectedItem.equals("Go back")) {
                textField.setText("Choose a subject");
                showSubjects();
            }
        } catch (NullPointerException ignored) {}
    }

    public void showTopics() {
        listView.getItems().clear();
        for (String topic : topicsInSelectedSubject) {
            if (!topicsInSelectedCourse.contains(topic)) {
                listView.getItems().add(topic);
            }
        }
        listView.getItems().add("Go back");
        textField.setText("Choose a topic");
    }

    public void showSubjects() {
        listView.getItems().clear();
        for (String subject : subjects) {
            listView.getItems().add(subject);
        }
        textField.setText("Choose a subject");
    }

    public void onShow() {
        listView.getItems().clear();
        topicsInSelectedCourse = database.getTopicsForSelectedCourse(courseID);
        switch (showButton.getText()) {
            case "", "Show your topics" -> {
                textField.setText("Current topics in " + courseName + " #" + courseID);
                showButton.setText("Show topics to add");
                rightButton.setText("Remove selected topic");
                for (String topic : topicsInSelectedCourse) {
                    listView.getItems().add(topic);
                }
            }
            case "Show topics to add" -> {
                textField.setText("Choose a subject");
                showButton.setText("Show your topics");
                rightButton.setText("Add selected topic");
                for (String subject : subjects) {
                    if (!topicsInSelectedCourse.contains(subject))
                        listView.getItems().add(subject);
                }
            }
        }
    }

    public void onHomeClick() throws IOException {
        SceneLoader.getInstance().loadMainMenu(homeButton);
    }
}