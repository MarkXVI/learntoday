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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.ResourceBundle;


public class QuizEditorController implements Initializable {
    DBConnection database = ConnectionStorage.getInstance().getConnection();
    @FXML
    TextField inputQuestion;
    @FXML
    TextField inputAlt1; //correct answer
    @FXML
    TextField inputAlt2;
    @FXML
    TextField inputAlt3;
    @FXML
    TextField inputAlt4;
    @FXML
    Text topicTitle;
    @FXML
    Button homeButton;

    public QuizEditorController() throws SQLException {
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void submitQuiz() throws SQLException {
        String question = inputQuestion.getText();
        String answer1 = inputAlt1.getText(); //correct answer
        String answer2 = inputAlt2.getText();
        String answer3 = inputAlt3.getText();
        String answer4 = inputAlt4.getText();

        database.addQuestion(question, topicTitle.getText());

        database.addAlt(answer1, 1, question);
        database.addAlt(answer2, 0, question);
        database.addAlt(answer3, 0, question);
        database.addAlt(answer4, 0, question);
    }

    public void onHomeClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/MainMenu.fxml"));
        Stage stage = (Stage) homeButton.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add("View/Style.css");
        stage.setScene(scene);
        stage.show();
    }

    public void setText(String topic){
        topicTitle.setText(topic);
    }

}
