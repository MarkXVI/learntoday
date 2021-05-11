package Controller;

import Model.ConnectionStorage;
import Model.DBConnection;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
    Button homeButton;
    @FXML
    Pane confirmPane;
    @FXML
    Pane msgError;

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

        database.addAlt(answer1, 1, question); //correct answer
        database.addAlt(answer2, 0, question);
        database.addAlt(answer3, 0, question);
        database.addAlt(answer4, 0, question);

        confirmPane.setVisible(false);
        submitNotification();
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

    public void showPane(ActionEvent actionEvent) {
        confirmPane.setVisible(true);
    }

    public void hidePane(ActionEvent actionEvent) {
        confirmPane.setVisible(false);
    }

    public void submitNotification(){
        msgError.setVisible(true);
        FadeTransition ft = new FadeTransition(Duration.seconds(6), msgError);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.play();
    }

}
