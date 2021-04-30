package Controller;

import Functionality.User;
import Model.ConnectionStorage;
import Model.DB_Connection;
import Model.UserStorage;
import javafx.event.ActionEvent;
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

public class MenuController implements Initializable {
    @FXML
    Button logoutButton;
    @FXML
    Text SignedInText;
    DB_Connection database = ConnectionStorage.getInstance().getConnection();
    @FXML
    ListView TopicList;

    UserStorage userStorage = UserStorage.getInstance();
    User user = userStorage.currentUser();

    public MenuController() throws SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String FirstName = user.getFirstname();
        String LastName = user.getLastname();
        SignedInText.setText("Signed in as: " + FirstName + " " + LastName);
        TopicList.getItems().add("History");
        TopicList.getItems().add("Item 2");
        TopicList.getItems().add("Item 3");
    }

    public void onMouseClick(MouseEvent click) throws SQLException {
        if (click.getClickCount() == 2){
            String currentItemSelected = TopicList.getSelectionModel().getSelectedItem().toString();
            TopicList.getItems().clear();
            ArrayList<Object> subjects = database.get_Subjects(currentItemSelected);
            for(Object subject: subjects){
                TopicList.getItems().add(subject.toString());
            }

        }
    }

    public void onLogoutClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/LoginScreen.fxml")); // ↓↓↓↓ Switches scene to login screen.
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add("View/Style.css");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


}
