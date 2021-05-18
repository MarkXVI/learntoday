package Controller;

import Functionality.SceneLoader;
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

    public AddCourseController() throws SQLException {}

    public void onHomeClick() throws IOException {
        SceneLoader.getInstance().LoadMainMenu(homeButton);
    }

    public void onAddClick() throws SQLException {
        ArrayList<Integer> existingIDs = database.getCourseIDs();
        int randomID;
        do {
            randomID = (int) (Math.random() * (99999 - 10000) + 10000);
        } while (existingIDs.contains(randomID));
        database.addCourse(randomID, userInput.getText());
        database.addUserToCourse(randomID, UserStorage.getInstance().currentUser().getUsername());
        courseIDText.setText("Course ID: " + randomID);
        showElements(true);
    }

    public void showElements(boolean bool) {
        whiteRectangle.setVisible(bool);
        confirmationText.setVisible(bool);
        courseIDText.setVisible(bool);
        addButton.setVisible(!bool);
    }
}