package Controller;

import Model.QuizStorage;
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


public class ResultsController implements Initializable {
    @FXML
    Text totalScore;
    @FXML
    Text perfectScore;
    @FXML
    Button uploadButton;
    @FXML
    Button finishButton;

    int points = QuizStorage.getInstance().getPoints();
    int questions = QuizStorage.getInstance().getQuestions();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        perfectScore.setVisible(points == questions);
        totalScore.setText(points + "/" + questions);
    }

    public void onFinishClick(ActionEvent event) throws IOException {
        QuizStorage.getInstance().resetPoints();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/MainMenu.fxml"));
        Stage stage = (Stage) finishButton.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add("View/Style.css");
        stage.setScene(scene);
        stage.show();
    }

    public void onUploadClick(ActionEvent event) {

    }
}
