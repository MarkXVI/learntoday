package Controller;

import Functionality.Logic;
import Model.QuizStorage;
import Model.TopicStorage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setText(String text, String topic) {
        infoText.setText(text);
        topicText.setText(topic);
    }

    public void onHomeClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/MainMenu.fxml"));
        Stage stage = (Stage) homeButton.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add("View/Style.css");
        stage.setScene(scene);
        stage.show();
    }

    public void onQuizClick(ActionEvent actionEvent) throws IOException {
        try {
            String selectedTopic = TopicStorage.getInstance().getTopic();

            if (Logic.checkSufficientQuestions(selectedTopic)){
                QuizStorage.getInstance().add_questions(selectedTopic);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/QuizLayout.fxml"));
                Stage stage = (Stage) quizButton.getScene().getWindow();
                Scene scene = new Scene(loader.load());
                scene.getStylesheets().add("View/Style.css");
                QuizController quizController = loader.getController();
                quizController.set_Title(selectedTopic);

                stage.setScene(scene);
                stage.show();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
