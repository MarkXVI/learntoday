package Controller;

import Functionality.Logic;
import Functionality.SceneLoader;
import Functionality.User;
import Model.ConnectionStorage;
import Model.DBConnection;
import Model.QuizStorage;
import Model.UserStorage;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    DBConnection database = ConnectionStorage.getInstance().getConnection();
    @FXML
    Button logoutButton;
    @FXML
    Text signedInText;
    @FXML
    ListView<String> topicList;
    @FXML
    Button quizButton;
    @FXML
    Button readButton;
    @FXML
    Button manageCoursesButton;
    @FXML
    Button joinCourseButton;
    @FXML
    Button leaderboardsButton;
    @FXML
    MenuBar editBar;
    @FXML
    TextFlow teacherInfo;
    @FXML
    Text errorText;
    @FXML
    Text topicText;
    @FXML
    Pane msgError;
    @FXML
    Pane paneAbout;

    UserStorage userStorage = UserStorage.getInstance();
    User user = userStorage.currentUser();
    ArrayList<String> subjects;
    ArrayList<String> topics;

    public MenuController() throws SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        checkUserType();

        String firstName = user.getFirstname();
        String lastName = user.getLastname();
        signedInText.setText("Signed in as: " + firstName + " " + lastName);
        try {
            subjects = database.getSubjects();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        addSubjects();
    }

    public void checkUserType() {
        if (user.getTeacher() == 1) {
            editBar.setVisible(true);
            teacherInfo.setVisible(true);
            manageCoursesButton.setVisible(true);
            leaderboardsButton.setVisible(true);
        } else {
            editBar.setVisible(false);
            teacherInfo.setVisible(false);
            manageCoursesButton.setVisible(false);
            leaderboardsButton.setVisible(false);
        }
    }

    public void addSubjects() {
        topicText.setText("Subjects");
        for (Object subject: subjects) {
            topicList.getItems().add(subject.toString());
        }
    }

    public void onMouseClick(MouseEvent click) throws SQLException {
        try {
        if (click.getClickCount() == 2) {
            String currentItemSelected = topicList.getSelectionModel().getSelectedItem();
            if (subjects.contains(currentItemSelected)) {
                topicText.setText("Topics");
                topics = database.getTopics(currentItemSelected);
                topicList.getItems().clear();
                for (Object topic: topics) {
                    topicList.getItems().add(topic.toString());
                }
                topicList.getItems().add("Go Back");
            }
            if (currentItemSelected.equals("Go Back")) {
                topicList.getItems().clear();
                addSubjects();
            }
        }
        } catch (NullPointerException ignored) {}
    }

    public void errorChange(String text) {
        errorText.setText(text);
        msgError.setVisible(true);
        FadeTransition ft = new FadeTransition(Duration.seconds(6), msgError);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.play();
    }

    public void onQuizClick() {
        try {
            String selectedTopic = topicList.getSelectionModel().getSelectedItem();
            if (selectedTopic.equals("Continents") || selectedTopic.equals("Oceans")) {
                SceneLoader.getInstance().WorldMapLoader(selectedTopic, quizButton);
            }
            if (topics.contains(selectedTopic)) {
                if (Logic.checkSufficientQuestions(selectedTopic)){
                    QuizStorage.getInstance().addQuestions(selectedTopic);
                    QuizStorage.getInstance().QuizShuffle();
                    SceneLoader.getInstance().loadQuizMCTF(selectedTopic, quizButton, 0);
                }
            }
        } catch(Exception ex) {
            errorChange("Must choose a valid topic!");
        }
    }

    public void onReadClick() throws IOException, SQLException {
        try {
            QuizStorage.getInstance().setTopic(topicList.getSelectionModel().getSelectedItem());
            String selectedItem = QuizStorage.getInstance().getTopic();
            if (topics.contains(selectedItem)) {
                SceneLoader.getInstance().loadReadScenes(selectedItem, readButton);
            }
        } catch(NullPointerException ex){
            errorChange("You must choose a topic to read about!");
        }
    }

    public void onLogoutClick() throws IOException {
        SceneLoader.getInstance().loadLogScene(logoutButton);
    }

    public void onEditText() throws IOException, SQLException {
        try {
            String selectedItem = topicList.getSelectionModel().getSelectedItem();
            if (topics.contains(selectedItem)) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/WriteScreen.fxml"));
                Stage stage = (Stage) logoutButton.getScene().getWindow();
                Scene scene = new Scene(loader.load());
                scene.getStylesheets().add("View/Style.css");
                WriteController writeController = loader.getController();
                String text = database.getText(selectedItem);
                writeController.setText(text, selectedItem);

                stage.setScene(scene);
                stage.show();
            }
        } catch (NullPointerException npe) {
            errorChange("You must choose a topic to edit!");
        }
    }

    public void onEditQuestion() {
        try {
            String selectedItem = topicList.getSelectionModel().getSelectedItem();
            if (topics.contains(selectedItem)) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/QuizEditor.fxml"));
                Stage stage = (Stage) logoutButton.getScene().getWindow();
                Scene scene = new Scene(loader.load());
                scene.getStylesheets().add("View/Style.css");
                QuizEditorController quizEditorController = loader.getController();
                quizEditorController.setText(selectedItem);

                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            errorChange("You must choose a topic to add questions!");
        }
    }

    public void onManageCourses() {
        try {
            SceneLoader.getInstance().loadManage(manageCoursesButton);
        } catch (IOException ignored) {}
    }

    public void onJoinCourse() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/JoinCourse.fxml"));
            Stage stage = (Stage) joinCourseButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add("View/Style.css");

            stage.setScene(scene);
            stage.show();
        } catch (IOException ignored) {}
    }

    public void onLeaderboardsClick() {
        try {
            SceneLoader.getInstance().loadLeaderboard(leaderboardsButton);

        } catch (IOException ignored) {}
    }

    public void onAboutOpen() {
        paneAbout.setVisible(true);
    }

    public void onAboutClose() {
        paneAbout.setVisible(false);
    }
}