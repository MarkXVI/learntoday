package Controller;

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
import java.util.Random;
import java.util.ResourceBundle;


public class QuizController implements Initializable {

    DBConnection database = ConnectionStorage.getInstance().getConnection();
    @FXML
    Button homeButton;
    @FXML
    private Text topicTitle;
    @FXML
    Button alternative1;
    @FXML
    Button alternative2;
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
    ArrayList<String> questionIDs = QuizStorage.getInstance().get_questionIDs();
    static int numberOfQuestions = QuizStorage.getInstance().count_questions();
    String question;
    Random rand = new Random();

    public QuizController() throws SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            int randomInt = rand.nextInt(questionIDs.size());
            question = database.getQuestion(questionIDs.get(randomInt));
            alternatives = database.getAlternatives(questionIDs.get(randomInt));
            Collections.shuffle(alternatives);
            alternative1.setText(alternatives.get(0));
            alternative2.setText(alternatives.get(1));
            alternative3.setText(alternatives.get(2));
            alternative4.setText(alternatives.get(3));
            questionText.setText(question);
            questionNumber.setText((count + "/" + numberOfQuestions));
            questionIDs.remove(randomInt);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void onAlternativeClick(ActionEvent event) throws SQLException {
        ArrayList<Button> altButtons = new ArrayList<>() {
            {
                add(alternative1);
                add(alternative2);
                add(alternative3);
                add(alternative4);
            }
        };
        if (count > numberOfQuestions){
            nextButton.setText("Finish Quiz!");
        }
        Button clicked = (Button) event.getTarget();
        if (database.checkAnswer(clicked.getText())) {
            clicked.setStyle("-fx-background-color: #50C878");
            QuizStorage.getInstance().addPoint();
            for (Button button : altButtons) {
                button.setDisable(true);
            }
        } else {
            for (Button button : altButtons) {
                if (database.checkAnswer(button.getText())) {
                    button.setStyle("-fx-background-color: #50C878");
                }
                button.setDisable(true);
            }
            clicked.setStyle("-fx-background-color: Red");
        }
        nextButton.setVisible(true);
        nextButton.setDisable(false);
    }

    public void onHomeClick(ActionEvent actionEvent) throws IOException {
        count = 1;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/MainMenu.fxml"));
        Stage stage = (Stage) homeButton.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add("View/Style.css");
        stage.setScene(scene);
        stage.show();
    }

    public void nextQuestion(ActionEvent event) throws IOException {
        if(count <= numberOfQuestions){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/QuizLayout.fxml"));
            Stage stage = (Stage) nextButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add("View/Style.css");
            QuizController quizController = loader.getController();
            quizController.set_Title(topicTitle.getText());

            stage.setScene(scene);
            stage.show();
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

    public void set_Title(String topic){
        topicTitle.setText(topic);
        questionCount.setText("Question " + (count));
        count ++;
    }
}
