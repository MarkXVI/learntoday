package Controller;

import java.util.concurrent.TimeUnit;
import Model.DB_Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LoginController {

    DB_Connection database = new DB_Connection();
    @FXML
    Button registerButton;
    @FXML
    Button loginButton;
    @FXML
    TextField userInput;
    @FXML
    TextField passwordInput;

    public void onLoginClick(ActionEvent actionEvent) throws IOException{
        String username = userInput.getText();
        String password = passwordInput.getText();
        boolean check = database.check_login(username, password);
        if (check){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/MainMenu.fxml"));
            Stage stage = (Stage) loginButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add("View/Style.css");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }else{
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
