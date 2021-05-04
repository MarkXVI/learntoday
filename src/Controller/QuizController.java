package Controller;

import Model.ConnectionStorage;
import Model.DB_Connection;
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
    ArrayList<String> alternatives = new ArrayList<>();
    ArrayList<String> questionIDs = QuizStorage.getInstance().get_questionIDs();
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
        } catch (SQLException exception) {
            exception.printStackTrace();
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

    public void set_Title(String topic){
        topicTitle.setText(topic);
    }
}
