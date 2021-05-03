package Controller;

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
import java.util.ResourceBundle;


public class QuizController implements Initializable {
    @FXML
    Button homeButton;
    @FXML
    private Text topicTitle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void onHomeClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/MainMenu.fxml"));
        Stage stage = (Stage) homeButton.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add("View/Style.css");
        stage.setScene(scene);
        stage.show();
    }

    public void set_Title(String topic){
        topicTitle.setText(topic);
    }
}
