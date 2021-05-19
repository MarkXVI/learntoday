package Controller;

import Functionality.SceneLoader;
import Functionality.User;
import Model.ConnectionStorage;
import Model.DBConnection;
import Model.UserStorage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddCourseController {

    DBConnection database = ConnectionStorage.getInstance().getConnection();
    @FXML
    Button homeButton;
    @FXML
    Button addButton;
    @FXML
    TextField userInput;
    @FXML
    Rectangle whiteRectangle;
    @FXML
    Text confirmationText;
    @FXML
    Text courseIDText;

    UserStorage userStorage = UserStorage.getInstance();
    User user = userStorage.currentUser();

    ArrayList<String> courses;

    public AddCourseController() throws SQLException {}

    public void onHomeClick() throws IOException {
        SceneLoader.getInstance().loadMainMenu(homeButton);
    }

    public void onAddClick() throws SQLException {
        ArrayList<Integer> existingIDs = database.getCourseIDs();
        courses = database.getCurrentUsersCourseNames(database.getCurrentUsersCourseIDs(user.getUsername()));
        int randomID;
        if (!courses.contains(userInput.toString())) {
            do {
                randomID = (int) (Math.random() * (99999 - 10000) + 10000);
            } while (existingIDs.contains(randomID));
            database.addCourse(randomID, userInput.getText());
            database.addUserToCourse(randomID, UserStorage.getInstance().currentUser().getUsername());
            courseIDText.setText("Course ID: " + randomID);
            showElements(true);
        } else if (courses.contains(userInput.toString())) {
            courseIDText.setText("A course with the same name already exists!");
        }
    }

    public void showElements(boolean bool) {
        whiteRectangle.setVisible(bool);
        confirmationText.setVisible(bool);
        courseIDText.setVisible(bool);
    }
}