package Controller;

import Functionality.Logic;
import Functionality.SceneLoader;
import Model.ConnectionStorage;
import Model.DBConnection;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterController {
    @FXML
    Button goBackButton;
    @FXML
    Button registerButton;
    @FXML
    TextField firstNameInput;
    @FXML
    TextField lastNameInput;
    @FXML
    TextField usernameInput;
    @FXML
    TextField passwordInput;
    @FXML
    Text errorText;
    @FXML
    MenuButton accountType;
    @FXML
    Pane msgError;

    DBConnection database = ConnectionStorage.getInstance().getConnection();

    public RegisterController() throws SQLException {}

    public void onGoBackClick() throws IOException {
        SceneLoader.getInstance().loadLogScene(goBackButton);
    }

    public void onRegisterClick() throws IOException {
        String firstName = firstNameInput.getText();
        String lastName = lastNameInput.getText();
        String username = usernameInput.getText();
        String password = passwordInput.getText();
        int AccountType;
        if (accountType.getText().equals("Teacher")) {
            AccountType = 1;
        } else {
            AccountType = 0;
        }
        Logic logic = new Logic();
        boolean check = logic.checkValidRegister(firstName, lastName, username, password, accountType.getText());

        if (database.getAllUsernames().contains(username)) {
            errorText.setText("That username is not available!");
            setErrorText();
        }
        else if (check) {
            database.registerUser(firstName, lastName, username, password, AccountType);
            onGoBackClick();
        }
        else {
            errorText.setText("You must enter something in every field!");
            setErrorText();
        }
    }

    public void setErrorText() {
        msgError.setVisible(true);
        FadeTransition ft = new FadeTransition(Duration.seconds(6), msgError);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.play();
    }

    public void onTypeChoice(ActionEvent actionEvent) {
        MenuItem menuItem = (MenuItem) actionEvent.getSource();
        accountType.setText(menuItem.getText());
    }
}