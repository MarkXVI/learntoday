package Controller;

import Functionality.Logic;
import Model.DB_Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterController {
    @FXML
    Button GoBackButton;
    @FXML
    Button registerButton;
    @FXML
    TextField FirstNameInput;
    @FXML
    TextField LastNameInput;
    @FXML
    TextField UsernameInput;
    @FXML
    TextField PasswordInput;
    @FXML
    MenuButton accountType;

    DB_Connection database = new DB_Connection();

    public RegisterController() throws SQLException {
    }

    public void onGoBackClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/LoginScreen.fxml"));
        Stage stage = (Stage) GoBackButton.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add("View/Style.css");
        stage.setScene(scene);
        stage.show();
    }

    public void onRegisterClick(ActionEvent actionEvent) throws IOException, SQLException {
        String FirstName = FirstNameInput.getText();
        String LastName = LastNameInput.getText();
        String Username = UsernameInput.getText();
        String Password = PasswordInput.getText();
        int AccountType;
        if (accountType.getText().equals("Teacher")){
            AccountType = 1;
        }else{
            AccountType = 0;
        }
        Logic logic = new Logic();
        DB_Connection db_connection = new DB_Connection();
        boolean check = logic.check_ValidRegister(FirstName, LastName, Username, Password, accountType.getText());
        if (check){
            db_connection.register_user(FirstName, LastName, Username, Password, AccountType);
            onGoBackClick(actionEvent);
        }else{
            System.out.println("You must enter something in every field!");
        }
    }

    public void onTypeChoice(ActionEvent actionEvent){
        MenuItem menuItem = (MenuItem) actionEvent.getSource();
        accountType.setText(menuItem.getText());
    }

}
