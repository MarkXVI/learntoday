package Controller;

import Functionality.Logic;
import Functionality.SceneLoader;
import Functionality.TextToSpeech;
import Model.QuizStorage;
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

    TextToSpeech tts = new TextToSpeech();

    public ReadController() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setText(String text, String topic) {
        infoText.setText(text);
        topicText.setText(topic);
    }

    public void onHomeClick() throws IOException {
        SceneLoader.getInstance().loadMainMenu(homeButton);
    }

    public void onQuizClick() throws IOException {
        try {
            String selectedTopic = QuizStorage.getInstance().getTopic();
            if (selectedTopic.equals("Continents") || selectedTopic.equals("Oceans")) {
                SceneLoader.getInstance().WorldMapLoader(selectedTopic, quizButton);
            }
            if (Logic.checkSufficientQuestions(selectedTopic)){
                QuizStorage.getInstance().addQuestions(selectedTopic);
                QuizStorage.getInstance().QuizShuffle();
                SceneLoader.getInstance().loadQuizMCTF(selectedTopic, quizButton, 0);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        }

        public void OnTextToSpeech() throws Exception {
        tts.setText(infoText.getText());
        tts.run();
    }

    public void OnTextToSpeechPause() throws InterruptedException {
        tts.pause();
    }
}
