package Controller;

import Functionality.User;
import Model.ConnectionStorage;
import Model.DBConnection;
import Model.QuizStorage;
import Model.UserStorage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    Button logoutButton;
    @FXML
    Text SignedInText;
    DBConnection database = ConnectionStorage.getInstance().getConnection();
    @FXML
    ListView TopicList;
    @FXML
    Button quizButton;
    @FXML
    Button readButton;

    UserStorage userStorage = UserStorage.getInstance();
    User user = userStorage.currentUser();
    ArrayList<Object> topics;
    ArrayList<Object> subjects;

    public MenuController() throws SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String FirstName = user.getFirstname();
        String LastName = user.getLastname();
        SignedInText.setText("Signed in as: " + FirstName + " " + LastName);
        try {
            topics = database.getTopics();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        addTopics();
    }

    public void addTopics() {
        for (Object topic: topics) {
            TopicList.getItems().add(topic.toString());
        }
    }

    public void onMouseClick(MouseEvent click) throws SQLException {
        if (click.getClickCount() == 2) {
            String currentItemSelected = TopicList.getSelectionModel().getSelectedItem().toString();
            if (topics.contains(currentItemSelected)) {
                subjects = database.getSubjects(currentItemSelected);
                TopicList.getItems().clear();
                for (Object subject: subjects){
                    TopicList.getItems().add(subject.toString());
                }
                TopicList.getItems().add("Go Back");
            }
            if (currentItemSelected.equals("Go Back")) {
                TopicList.getItems().clear();
                addTopics();
            }
        }
    }

    public void onQuizClick(ActionEvent actionEvent) throws IOException, SQLException {
        String selectedTopic = TopicList.getSelectionModel().getSelectedItem().toString();
        if (subjects.contains(selectedTopic)) {
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
    }

    public void onReadClick(ActionEvent actionEvent) throws IOException, SQLException {
        String selectedItem = TopicList.getSelectionModel().getSelectedItem().toString();
        if (subjects.contains(selectedItem)) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/ReadScreen.fxml"));
            Stage stage = (Stage) readButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add("View/Style.css");

            ReadController readController = loader.getController();
            String text = database.getText(selectedItem);
            readController.setText(text, selectedItem);

            stage.setScene(scene);
            stage.show();
        }
    }

    public void onLogoutClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/LoginScreen.fxml")); // ↓↓↓↓ Switches scene to login screen.
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add("View/Style.css");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
