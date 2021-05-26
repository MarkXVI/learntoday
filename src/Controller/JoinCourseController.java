package Controller;

import Functionality.SceneLoader;
import Model.ConnectionStorage;
import Model.DBConnection;
import Model.UserStorage;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

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
    @FXML
    Pane errorPane;

    public JoinCourseController() throws SQLException {}

    public void onHomeClick() throws IOException {
        SceneLoader.getInstance().loadMainMenu(homeButton);
    }

    public void onJoinClick() throws SQLException {
        try {
            int courseID = Integer.parseInt(userInput.getText());
            ArrayList<Integer> existingIDs = database.getCourseIDs();

            if (existingIDs.contains(courseID)) {
                database.addUserToCourse(courseID, UserStorage.getInstance().currentUser().getUsername());
                showMessage("Course joined!", "#caffc4", "#3fe469");
            } else {
                showMessage("Course doesn't exist!", "#ffaeae", "#ff3232");
            }
        } catch (NumberFormatException exception) {
            showMessage("Course doesn't exist!", "#ffaeae", "#ff3232");
        }
    }

    public void showMessage(String message, String rectColor, String textColor) {
        confirmationText.setText(message);
        confirmationText.setFill(Paint.valueOf(textColor));
        whiteRectangle.setFill(Paint.valueOf(rectColor));
        errorPane.setVisible(true);
        FadeTransition ft = new FadeTransition(Duration.seconds(6), errorPane);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.play();
        if (confirmationText.getText().equals("Course joined!")) {
            joinButton.setVisible(false);
        }
    }
}
