package Controller;

import Functionality.SceneLoader;
import Model.QuizStorage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

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

    public void onFinishClick() throws IOException {
        SceneLoader.getInstance().LoadMainMenu(finishButton);

    }

    public void onUploadClick() {

    }
}
