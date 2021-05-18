package Controller;

import Functionality.SceneLoader;
import Functionality.User;
import Model.ConnectionStorage;
import Model.DBConnection;
import Model.UserStorage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
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

    UserStorage userStorage = UserStorage.getInstance();
    User user = userStorage.currentUser();

    ArrayList<String> courses;
    ArrayList<Object> topics;

    public LeaderboardsController() throws SQLException {}

    public void onHomeClick() throws IOException {
        SceneLoader.getInstance().LoadMainMenu(homeButton);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            String username = user.getUsername();
            courses = database.getCurrentUsersCourses(username);
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
                    topics = database.getTopics(currentItemSelected);
                    coursesList.getItems().clear();
                    for (Object topic : topics) {
                        coursesList.getItems().add(topic.toString());
                    }
                    coursesList.getItems().add("Go Back");
                }
                if (currentItemSelected.equals("Go Back")) {
                    coursesList.getItems().clear();
                    addCourses();
                }
            }
        } catch (NullPointerException ignored) {}
    }
}
