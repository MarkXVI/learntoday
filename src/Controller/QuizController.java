package Controller;

import Functionality.SceneLoader;
import Model.ConnectionStorage;
import Model.DBConnection;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;


public class QuizController implements Initializable {

    DBConnection database = ConnectionStorage.getInstance().getConnection();
    @FXML
    Button homeButton;
    @FXML
    private Text topicTitle;
    @FXML
    Button alternative1; //True
    @FXML
    Button alternative2; //False
    @FXML
    Button alternative3;
    @FXML
    Button alternative4;
    @FXML
    Button nextButton;
    @FXML
    Text questionText;
    @FXML
    Text questionCount;
    @FXML
    Text questionNumber;

    static int count = 1;
    ArrayList<String> alternatives = new ArrayList<>();
    ArrayList<String> quizQue = QuizStorage.getInstance().get_questionIDs();
    int numberOfQuestions = QuizStorage.getInstance().getQuestions();
    String question;
    String questionType;


    public QuizController() throws SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            question = database.getQuestion(quizQue.get(count - 1));
            questionType = database.getQuestionType(quizQue.get(count - 1));
            if(questionType.equals("MC")) {
                alternatives = database.getAlternatives(quizQue.get(count - 1));
                Collections.shuffle(alternatives);
                alternative1.setText(alternatives.get(0));
                alternative2.setText(alternatives.get(1));
                alternative3.setText(alternatives.get(2));
                alternative4.setText(alternatives.get(3));
            }
            questionText.setText(question);
            questionNumber.setText((count + "/" + numberOfQuestions));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void onAlternativeClick(ActionEvent event) throws SQLException {
        ArrayList<Button> altButtons = new ArrayList<>() {
            {
                add(alternative1);
                add(alternative2);
            }
        };

        if(database.getQuestionType(quizQue.get(count - 1)).equals("MC")) {
            altButtons.add(alternative3);
            altButtons.add(alternative4);
        }
        if (count > numberOfQuestions){
            nextButton.setText("Finish Quiz!");
        }
        Button clicked = (Button) event.getTarget();
        if (database.checkAnswer(clicked.getText(), quizQue.get(count-1))) {
            clicked.setStyle("-fx-background-color: #50C878");
            QuizStorage.getInstance().addPoint();
            for (Button button : altButtons) {
                button.setDisable(true);
            }
        } else {
            for (Button button : altButtons) {
                if (database.checkAnswer(button.getText(), quizQue.get(count-1))) {
                    button.setStyle("-fx-background-color: #50C878");
                }
                button.setDisable(true);
            }
            clicked.setStyle("-fx-background-color: Red");
        }
        nextButton.setVisible(true);
        nextButton.setDisable(false);
    }

    public void onHomeClick() throws IOException {
        count = 1;
        SceneLoader.getInstance().LoadMainMenu(homeButton);
    }

    public void nextQuestion() throws IOException, SQLException {
        count ++;
        if(count <= numberOfQuestions){
            SceneLoader.getInstance().LoadQuizMCTF(topicTitle.getText(), nextButton, count-1);
        }else{
            count = 1;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/QuizResults.fxml"));
            Stage stage = (Stage) nextButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add("View/Style.css");

            stage.setScene(scene);
            stage.show();
        }
    }

    public void setTitle(String topic){
        topicTitle.setText(topic);
        questionCount.setText("Question " + (count));
    }
}
