package Controller;

import Functionality.Pairs;
import Functionality.SceneLoader;
import Functionality.User;
import Model.ConnectionStorage;
import Model.DBConnection;
import Model.UserStorage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LeaderboardsController implements Initializable {

    DBConnection database = ConnectionStorage.getInstance().getConnection();
    @FXML
    Button homeButton;
    @FXML
    ListView<String> coursesList;
    @FXML
    TableView<Pairs> studentTable;
    @FXML
    TableColumn<Pairs, String> studentColumn;
    @FXML
    TableColumn<Pairs, Integer> scoreColumn;
    @FXML
    Button backButton;

    UserStorage userStorage = UserStorage.getInstance();
    User user = userStorage.currentUser();
    int course;
    String topic;

    ArrayList<String> courses;
    ArrayList<String> topics;

    public LeaderboardsController() throws SQLException {}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            courses = database.getCurrentUsersCourseNames(database.getCurrentUsersCourseIDs(user.getUsername()));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        addCourses();
    }

    public void addCourses() {
        for (String course : courses) {
            coursesList.getItems().add(course);
        }
    }

    public void onMouseClick(MouseEvent click) throws SQLException {
        try {
            if (click.getClickCount() == 2) {
                String currentItemSelected = coursesList.getSelectionModel().getSelectedItem();
                if (courses.contains(currentItemSelected)) {
                    course = database.getIDForSelectedCourse(currentItemSelected, user.getUsername());
                    topics = database.getTopicsFromCourse(course);
                    coursesList.getItems().clear();
                    for (Object topic : topics) {
                        coursesList.getItems().add(topic.toString());
                    }
                    coursesList.getItems().add("Go Back");
                }else if (topics.contains(currentItemSelected)){
                    topic = currentItemSelected;
                    studentColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                    scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
                    ArrayList<Pairs> users = database.getCourseUsers(course, topic);
                    ObservableList<Pairs> userList = FXCollections.observableList(users);
                    studentTable.setItems(userList);
                    studentTable.setVisible(true);
                    backButton.setVisible(true);
                }else if (currentItemSelected.equals("Go Back")) {
                    coursesList.getItems().clear();
                    addCourses();
                }
            }
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    public void onBackClick(){
        studentTable.setVisible(false);
        backButton.setVisible(false);
    }

    public void onHomeClick() throws IOException {
        SceneLoader.getInstance().loadMainMenu(homeButton);
    }
}
