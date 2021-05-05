package Controller;

import Model.ConnectionStorage;
import Model.DBConnection;
import Model.QuizStorage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class WriteController implements Initializable {
    @FXML
    Button submitButton;
    @FXML
    Button homeButton;
    @FXML
    Button submitQuestionButton;
    @FXML
    private TextArea infoText;
    @FXML
    Text topicNameText;
    @FXML
    TableColumn colQuestion;
    @FXML
    TableColumn colQuizName;
    @FXML
    TableColumn colQuestionID;
    @FXML
    TableColumn colMultichoice;
    @FXML
    TableView tableQuestion;
    ArrayList<String> tableList = QuizStorage.getInstance().getTable(topicNameText.getText());
    ObservableList<String> questions = (ObservableList<String>) tableList;

    DBConnection database = ConnectionStorage.getInstance().getConnection();

    public WriteController() throws SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableQuestion.setItems(questions);
    }

    public void setQuestionsTable() throws SQLException {
        String topicName = topicNameText.getText();

        questions = FXCollections.observableArrayList();
        questions.addAll(QuizStorage.getInstance().getTable(topicName));
        tableQuestion.setItems(questions);
    }

    public void setText(String text, String topic) {
        infoText.setText(text);
        topicNameText.setText(topic);
    }

    public void onHomeClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/MainMenu.fxml"));
        Stage stage = (Stage) homeButton.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add("View/Style.css");
        stage.setScene(scene);
        stage.show();
    }

    public void onSubmitClick(ActionEvent actionEvent) {
        String topicName = topicNameText.getText();
        String text = infoText.getText();
        database.submitText(topicName, text);
    }

    public void onSubmitQuestionClick(ActionEvent actionEvent) {
        String topicName = topicNameText.getText();
        String text = infoText.getText();
        database.submitText(topicName, text);
    }
}
