package Controller;

import Model.ConnectionStorage;
import Model.DB_Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    Button logoutButton;
    @FXML
    Text SignedInText;
    DB_Connection database = ConnectionStorage.getInstance().getConnection();

    public MenuController() throws SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void receiveData(String FirstName, String LastName){
        SignedInText.setText("Signed in as: " + FirstName + " " + LastName);
    }
    public void onLogoutClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/LoginScreen.fxml"));
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add("View/Style.css");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


}
