package Controller;

import Functionality.SceneLoader;
import Functionality.User;
import Model.ConnectionStorage;
import Model.DBConnection;
import Model.UserStorage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddCourseController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        confirmationText.setVisible(false);
        showElements(false);
    }

    public void onHomeClick() throws IOException {
        SceneLoader.getInstance().loadMainMenu(homeButton);
    }

    public void onAddClick() throws SQLException {
        ArrayList<Integer> existingIDs = database.getCourseIDs();
        courses = database.getCurrentUsersCourseNames(user.getUsername());
        int randomID;
        if (!courses.contains(userInput.getText()) && !userInput.getText().equals("")) {
            do {
                randomID = (int) (Math.random() * (99999 - 10000) + 10000);
            } while (existingIDs.contains(randomID));
            database.addCourse(randomID, userInput.getText());
            database.addUserToCourse(randomID, user.getUsername());
            courseIDText.setText("Course ID: " + randomID);
            confirmationText.setVisible(true);
            showElements(true);
        } else if (courses.contains(userInput.getText())) {
            courseIDText.setText("A course with the same name already exists!");
            confirmationText.setVisible(false);
        }
        showElements(true);
    }

    public void showElements(boolean bool) {
        whiteRectangle.setVisible(bool);
        courseIDText.setVisible(bool);
    }
}