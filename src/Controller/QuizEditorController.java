package Controller;

import Functionality.Logic;
import Functionality.SceneLoader;
import Model.ConnectionStorage;
import Model.DBConnection;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class QuizEditorController implements Initializable {
    DBConnection database = ConnectionStorage.getInstance().getConnection();
    @FXML
    TextField inputQuestion;
    @FXML
    TextField inputAlt1;
    @FXML
    TextField inputAlt2;
    @FXML
    TextField inputAlt3;
    @FXML
    TextField inputAlt4;
    @FXML
    Text topicTitle;
    @FXML
    Text errorText;
    @FXML
    Button homeButton;
    @FXML
    Pane confirmPane;
    @FXML
    Pane msgError;
    @FXML
    Rectangle errorRect;


    public QuizEditorController() throws SQLException {
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void submitQuiz(){
        String question = inputQuestion.getText();
        String answer1 = inputAlt1.getText(); //correct answer
        String answer2 = inputAlt2.getText();
        String answer3 = inputAlt3.getText();
        String answer4 = inputAlt4.getText();

        if(Logic.checkValidQuiz(question, answer1, answer2, answer3, answer4)){
            database.addQuestion(question, topicTitle.getText(), "MC");

            database.addAlt(answer1, 1, question); //correct answer
            database.addAlt(answer2, 0, question);
            database.addAlt(answer3, 0, question);
            database.addAlt(answer4, 0, question);

            confirmPane.setVisible(false);
            submitNotification();
        }else{
            errorText.setText("Empty Alternative!");
            errorText.setFill(Paint.valueOf("#ff3232"));
            errorRect.setFill(Paint.valueOf("#ffaeae"));
            msgError.setVisible(true);
            FadeTransition ft = new FadeTransition(Duration.seconds(6), msgError);
            ft.setFromValue(1);
            ft.setToValue(0);
            ft.play();
        }

    }

    public void onHomeClick() throws IOException {
        SceneLoader.getInstance().loadMainMenu(homeButton);
    }

    public void setText(String topic){
        topicTitle.setText(topic);
    }

    public void showPane() {
        confirmPane.setVisible(true);
    }

    public void hidePane() {
        confirmPane.setVisible(false);
    }

    public void submitNotification(){
        errorText.setText("Quiz submitted!");
        errorText.setFill(Paint.valueOf("#3fe469"));
        errorRect.setFill(Paint.valueOf("#caffc4"));
        msgError.setVisible(true);
        FadeTransition ft = new FadeTransition(Duration.seconds(6), msgError);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.play();
    }

}
