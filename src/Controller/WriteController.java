package Controller;

import Functionality.SceneLoader;
import Model.ConnectionStorage;
import Model.DBConnection;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class WriteController implements Initializable {
    @FXML
    Button submitButton;
    @FXML
    Button homeButton;
    @FXML
    private TextArea infoText;
    @FXML
    Text topicNameText;
    @FXML
    Pane confirmPane;
    @FXML
    Pane msgError;

    DBConnection database = ConnectionStorage.getInstance().getConnection();

    public WriteController() throws SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setText(String text, String topic) {
        infoText.setText(text);
        topicNameText.setText(topic);
    }

    public void onHomeClick() throws IOException {
        SceneLoader.getInstance().LoadMainMenu(homeButton);
    }

    public void onSubmitClick() {
        String topicName = topicNameText.getText();
        String text = infoText.getText();
        database.submitText(topicName, text);
        submitNotification();
        confirmPane.setVisible(false);
    }

    public void showPane() {
        confirmPane.setVisible(true);
    }

    public void hidePane() {
        confirmPane.setVisible(false);
    }

    public void submitNotification(){
        msgError.setVisible(true);
        FadeTransition ft = new FadeTransition(Duration.seconds(6), msgError);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.play();
    }
}
