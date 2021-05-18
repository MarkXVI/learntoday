package Controller;

import Functionality.SceneLoader;
import Model.ConnectionStorage;
import Model.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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

    public EditCourseController() throws SQLException {}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setText(String course, String courseID) {
        textField.setText(course + " #" + courseID);
    }

    public void onAddNewTopic() {

    }

    public void onMouseClick(ActionEvent actionEvent) {

    }

    public void onRemoveStudent() {

    }

    public void onHomeClick() throws IOException {
        SceneLoader.getInstance().loadMainMenu(homeButton);
    }
}
