package Controller;

import Functionality.Logic;
import Functionality.SceneLoader;
import Model.QuizStorage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReadController implements Initializable {
    @FXML
    Button ttsButton;
    @FXML
    Button homeButton;
    @FXML
    Button quizButton;
    @FXML
    private TextArea infoText;
    @FXML
    private Text topicText;

    public ReadController() throws SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setText(String text, String topic) {
        infoText.setText(text);
        topicText.setText(topic);
    }

    public void onHomeClick(ActionEvent actionEvent) throws IOException {
        SceneLoader.getInstance().LoadMainMenu(homeButton);
    }

    public void onQuizClick(ActionEvent actionEvent) throws IOException {
        try {
            String selectedTopic = QuizStorage.getInstance().getTopic();

            if (Logic.checkSufficientQuestions(selectedTopic)){
                SceneLoader.getInstance().LoadQuizMCTF(selectedTopic, quizButton, 0);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
