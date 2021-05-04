package Controller;

import Functionality.Logic;
import Model.ConnectionStorage;
import Model.DB_Connection;
import Model.QuizStorage;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.skin.ButtonSkin;
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

    DB_Connection database = ConnectionStorage.getInstance().getConnection();
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
    Text questionText;
    @FXML
    Text questionCount;

    static int count = 0;
    ArrayList<String> alternatives = new ArrayList<>();
    static ArrayList<String> questionIDs = QuizStorage.getInstance().get_questionIDs();
    String question;
    Random rand = new Random();

    public QuizController() throws SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            int randomInt = rand.nextInt(questionIDs.size());
            question = database.get_Question(questionIDs.get(randomInt));
            alternatives = database.get_Alternatives(questionIDs.get(randomInt));
            Collections.shuffle(alternatives);
            alternative1.setText(alternatives.get(0));
            alternative2.setText(alternatives.get(1));
            alternative3.setText(alternatives.get(2));
            alternative4.setText(alternatives.get(3));
            questionText.setText(question);
            questionIDs.remove(randomInt);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void onAlternativeClick(ActionEvent event) throws SQLException {
        ArrayList<Button> altButtons = new ArrayList<>(){
            {
                add(alternative1);
                add(alternative2);
                add(alternative3);
                add(alternative4);
            }};
        Button clicked = (Button) event.getTarget();
        if(database.checkAnswer(clicked.getText())){
            clicked.setStyle("-fx-background-color: #50C878");
            for(Button button: altButtons){
                button.setDisable(true);
            }
        }else{
            for(Button button: altButtons){
                if (database.checkAnswer(button.getText())){
                    button.setStyle("-fx-background-color: #50C878");
                }
                button.setDisable(true);
            }
            clicked.setStyle("-fx-background-color: Red");
        }
    }

    public void onHomeClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/MainMenu.fxml"));
        Stage stage = (Stage) homeButton.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add("View/Style.css");
        stage.setScene(scene);
        stage.show();
    }

    public void nextQuestion(ActionEvent event){

    }

    public void set_Title(String topic){
        topicTitle.setText(topic);
        count += 1;
        questionCount.setText("Question " + count);
    }
}
