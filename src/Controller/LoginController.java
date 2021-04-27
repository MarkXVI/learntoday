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

    DB_Connection database;
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
            Stage stage = (Stage) loginButton.getScene().getWindow();
            handleNextScene(actionEvent, username, password);
            stage.close();
        }else{
            passwordInput.clear();
            passwordInput.promptTextProperty().setValue("Wrong Password!");
            passwordInput.setStyle("-fx-prompt-text-fill: Red");
        }
    }

    public void onRegisterClick(ActionEvent actionEvent) {
        String username = userInput.getText();
        String password = passwordInput.getText();
        database.register_user(username, password);
    }

    public Stage handleNextScene(ActionEvent event, String username, String password) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/MainMenu.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(new Scene(loader.load()));

        MenuController controller = loader.getController();
        stage.show();
        return stage;
    }

}
