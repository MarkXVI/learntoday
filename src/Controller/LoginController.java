package Controller;

import Functionality.SceneLoader;
import Model.ConnectionStorage;
import Model.DBConnection;
import Model.UserStorage;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LoginController {

    DBConnection database = ConnectionStorage.getInstance().getConnection();
    @FXML
    Button registerButton;
    @FXML
    Button loginButton;
    @FXML
    TextField userInput;
    @FXML
    TextField passwordInput;
    @FXML
    Pane msgError;

    public LoginController() throws SQLException {
    }

    public void onLoginClick() throws IOException {
        String username = userInput.getText();
        String password = passwordInput.getText();
        boolean check = database.checkLogin(username, password);
        if (check) {
            List<Object> User = database.getUserinfo(username); // Gets a list of objects from Database which contains user information.

            String Username = User.get(0).toString(); // ↓↓↓↓ This part is required to share the User between controllers/scenes.
            String FirstName = User.get(1).toString();
            String LastName = User.get(2).toString();
            int AccountType = (int) User.get(3);
            UserStorage userStorage = UserStorage.getInstance();
            userStorage.createUser(Username, FirstName, LastName, AccountType);

            SceneLoader.getInstance().LoadMainMenu(loginButton);
        } else {
            msgError.setVisible(true);
            FadeTransition ft = new FadeTransition(Duration.seconds(6), msgError);
            ft.setFromValue(1);
            ft.setToValue(0);
            ft.play();
            passwordInput.clear();
        }
    }

    public void onRegisterClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/RegisterScreen.fxml"));
        Stage stage = (Stage) registerButton.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add("View/Style.css");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
