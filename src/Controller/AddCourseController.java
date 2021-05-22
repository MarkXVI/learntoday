package Controller;

import Functionality.SceneLoader;
import Functionality.User;
import Model.ConnectionStorage;
import Model.DBConnection;
import Model.UserStorage;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

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
    @FXML
    Pane confirmationWindow;
    @FXML
    Button backButton;

    UserStorage userStorage = UserStorage.getInstance();
    User user = userStorage.currentUser();

    ArrayList<String> courses;

    public AddCourseController() throws SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void onHomeClick() throws IOException {
        SceneLoader.getInstance().loadMainMenu(homeButton);
    }


    public void onAddClick() throws SQLException {
        courseIDText.setText("");
        ArrayList<Integer> existingIDs = database.getCourseIDs();
        courses = database.getCurrentUsersCourseNames(user.getUsername());
        int randomID;
        if (!courses.contains(userInput.getText()) && !userInput.getText().equals("")) {
            do{
                randomID = (int) (Math.random() * (99999 - 10000) + 10000);
            }while (existingIDs.contains(randomID));
            database.addCourse(randomID, userInput.getText());
            database.addUserToCourse(randomID, user.getUsername());
            courseIDText.setText("Course ID: " + randomID);
            confirmationText.setText("Course added!");
            confirmationText.setFill(Paint.valueOf("#32a852"));
            whiteRectangle.setFill(Paint.valueOf("#ffffff"));
        }else if (courses.contains(userInput.getText())) {
            confirmationText.setText("A course with the same\nname already exists");
            confirmationText.setFill(Paint.valueOf("#ff3232"));
            whiteRectangle.setFill(Paint.valueOf("#ffaeae"));
        }
        showElements();
    }

    public void onBackClick() throws IOException {
        SceneLoader.getInstance().loadManage(backButton);
    }

    public void showElements(){
        confirmationWindow.setVisible(true);
        FadeTransition ft = new FadeTransition(Duration.seconds(6), confirmationWindow);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.play();
    }
}