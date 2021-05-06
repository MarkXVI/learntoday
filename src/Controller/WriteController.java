package Controller;

import Model.ConnectionStorage;
import Model.DBConnection;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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

    public void onHomeClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/MainMenu.fxml"));
        Stage stage = (Stage) homeButton.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add("View/Style.css");
        stage.setScene(scene);
        stage.show();
    }

    public void onSubmitClick(ActionEvent actionEvent) {
        String topicName = topicNameText.getText();
        String text = infoText.getText();
        database.submitText(topicName, text);
        submitNotification();
        confirmPane.setVisible(false);
    }

    public void showPane(ActionEvent actionEvent) {
        confirmPane.setVisible(true);
    }

    public void hidePane(ActionEvent actionEvent) {
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
