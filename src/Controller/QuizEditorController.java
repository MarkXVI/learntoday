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

    public QuizEditorController() throws SQLException {
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void submitQuiz() {
        String question = inputQuestion.getText();
        String answer1 = inputAlt1.getText(); //correct answer
        String answer2 = inputAlt2.getText();
        String answer3 = inputAlt3.getText();
        String answer4 = inputAlt4.getText();

        database.addQuiz(question, answer1);
    }

}
