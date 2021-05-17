package Controller;

import Functionality.SceneLoader;
import Model.ConnectionStorage;
import Model.DBConnection;
import Model.UserStorage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class JoinCourseController {

    DBConnection database = ConnectionStorage.getInstance().getConnection();
    @FXML
    Button joinButton;
    @FXML
    Button homeButton;
    @FXML
    TextField userInput;
    @FXML
    Text confirmationText;
    @FXML
    Rectangle whiteRectangle;

    public JoinCourseController() throws SQLException {}

    public void onHomeClick() throws IOException {
        SceneLoader.getInstance().LoadMainMenu(homeButton);
    }

    public void onJoinClick() throws SQLException {
        try {
            int courseID = Integer.parseInt(userInput.getText());
            ArrayList<Integer> existingIDs = database.getCourseIDs();

            if (existingIDs.contains(courseID)) {
                database.addUserToCourse(courseID, UserStorage.getInstance().currentUser().getUsername());
                confirmationText.setText("Course joined!");
            } else {
                confirmationText.setText("Course doesn't exist");
            }
            showElements(true);
        } catch (NumberFormatException exception) {
            //Error message
        }
    }

    public void showElements(boolean bool) {
        whiteRectangle.setVisible(bool);
        confirmationText.setVisible(bool);
        if (confirmationText.getText().equals("Course joined!")) {
            joinButton.setVisible(!bool);
        }
    }

}
