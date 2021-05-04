package Controller;

import Functionality.Logic;
import Functionality.User;
import Model.ConnectionStorage;
import Model.DB_Connection;
import Model.UserStorage;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LoginController {

    DB_Connection database = ConnectionStorage.getInstance().getConnection();
    @FXML
    Button registerButton;
    @FXML
    Button loginButton;
    @FXML
    TextField userInput;
    @FXML
    TextField passwordInput;

    User user;

    public LoginController() throws SQLException {
    }

    public void onLoginClick(ActionEvent actionEvent) throws IOException {
        String username = userInput.getText();
        String password = passwordInput.getText();
        boolean check = database.check_login(username, password);
        if (check){
            List<Object> User = database.get_userinfo(username); // Gets a list of objects from Database which contains user information.

            String Username = User.get(0).toString(); // ↓↓↓↓ This part is required to share the User between controllers/scenes.
            String FirstName = User.get(1).toString();
            String LastName = User.get(2).toString();
            int AccountType = (int) User.get(3);
            UserStorage userStorage = UserStorage.getInstance();
            userStorage.currentUser().setFirstname(FirstName);
            userStorage.currentUser().setLastname(LastName);
            userStorage.currentUser().setUsername(Username);
            userStorage.currentUser().setTeacher(AccountType);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/MainMenu.fxml")); // ↓↓↓↓ Switches scene to Main Menu Screen.
            Parent root = loader.load();

            Stage stage = (Stage) loginButton.getScene().getWindow();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("View/Style.css");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } else {
            passwordInput.clear();
            passwordInput.promptTextProperty().setValue("Wrong Password!");
            passwordInput.setStyle("-fx-prompt-text-fill: Red");
        }
    }

    public void onRegisterClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/RegisterScreen.fxml"));
        Stage stage = (Stage) registerButton.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add("View/Style.css");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
