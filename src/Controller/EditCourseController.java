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
import javafx.scene.text.Text;

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
    ListView<String> studentsList;

    ArrayList<String> usernames;

    UserStorage userStorage = UserStorage.getInstance();
    User user = userStorage.currentUser();

    public EditCourseController() throws SQLException {}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            int courseID = database.getIDForSelectedCourse(database.getCurrentUsersCourseIDs(user.getUsername()));
            usernames = database.getUsernamesForCourse(courseID);
        } catch (SQLException ignored) {}
        addUsernames();
    }

    public void addUsernames() {
        for (String username : usernames) {
            studentsList.getItems().add(username);
        }
    }

    public void setText(String course, int courseID) {
        if (courseID != 0) {
            textField.setText(course + " #" + courseID);
        } else {
            textField.setText((course));
        }
    }

    public void onAddNewTopic() {

    }

    public void onMouseClick() {

    }

    public void onRemoveStudent() {

    }

    public void onHomeClick() throws IOException {
        SceneLoader.getInstance().loadMainMenu(homeButton);
    }
}
