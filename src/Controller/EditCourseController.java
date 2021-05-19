package Controller;

import Functionality.SceneLoader;
import Functionality.User;
import Model.ConnectionStorage;
import Model.DBConnection;
import Model.UserStorage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;


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
            String username = user.getUsername();
            int courseID = database.getIDForSelectedCourse(database.getCurrentUsersCourseIDs(username));
            usernames = database.getUsernamesForCourse(courseID);
        } catch (SQLException ignored) {}
        addUsernames();
    }

    public void addUsernames() {
        for (String username : usernames) {
            studentsList.getItems().add(username);
            studentsList.getItems().add("Go back");
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

    public void onMouseClick(MouseEvent click) {
        if (click.getClickCount() == 2 && studentsList.getSelectionModel().getSelectedItem().equals("Go back")) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/ManageCourses.fxml"));
                Stage stage = (Stage) studentsList.getScene().getWindow();
                Scene scene = new Scene(loader.load());
                scene.getStylesheets().add("View/Style.css");

                stage.setScene(scene);
                stage.show();
            } catch (IOException ignored) {}
        }
    }

    public void onRemoveStudent() throws SQLException {
        String selectedItem = studentsList.getSelectionModel().getSelectedItem();
        String username = user.getUsername();
        int courseID = database.getIDForSelectedCourse(database.getCurrentUsersCourseIDs(username));
        if (usernames.contains(selectedItem)) {
            database.removeUser(courseID, selectedItem);
            studentsList.getItems().remove(selectedItem);
        }
    }

    public void onHomeClick() throws IOException {
        SceneLoader.getInstance().loadMainMenu(homeButton);
    }
}
