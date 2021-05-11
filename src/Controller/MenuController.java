package Controller;

import Functionality.*;
import Model.ConnectionStorage;
import Model.DBConnection;
import Model.QuizStorage;
import Model.UserStorage;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
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
    public MenuItem editItem1;
    @FXML
    Button logoutButton;
    @FXML
    Text SignedInText;
    DBConnection database = ConnectionStorage.getInstance().getConnection();
    @FXML
    ListView<String> TopicList;
    @FXML
    Button quizButton;
    @FXML
    Button readButton;
    @FXML
    Button addCourseButton;
    @FXML
    MenuBar editBar;
    @FXML
    TextFlow teacherInfo;
    @FXML
    Text errorText;
    @FXML
    Pane msgError;
    @FXML
    Pane paneAbout;

    UserStorage userStorage = UserStorage.getInstance();
    User user = userStorage.currentUser();
    ArrayList<Object> subjects;
    ArrayList<Object> topics;

    public MenuController() throws SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        checkUserType();

        String FirstName = user.getFirstname();
        String LastName = user.getLastname();
        SignedInText.setText("Signed in as: " + FirstName + " " + LastName);
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
        } else {
            editBar.setVisible(false);
            teacherInfo.setVisible(false);
        }
    }

    public void addSubjects(){
        for(Object subject: subjects){
            TopicList.getItems().add(subject.toString());
        }
    }

    public void onMouseClick(MouseEvent click) throws SQLException {
        try{
        if (click.getClickCount() == 2){
            String currentItemSelected = TopicList.getSelectionModel().getSelectedItem();
            if(subjects.contains(currentItemSelected)){
                topics = database.getTopics(currentItemSelected);
                TopicList.getItems().clear();
                for(Object topic: topics){
                    TopicList.getItems().add(topic.toString());
                }
                TopicList.getItems().add("Go Back");
            }
            if (currentItemSelected.equals("Go Back")){
                TopicList.getItems().clear();
                addSubjects();
            }
            if(currentItemSelected.equals("Continents")){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/Continents.fxml"));
                Stage stage = (Stage) quizButton.getScene().getWindow();
                Scene scene = new Scene(loader.load());
                scene.getStylesheets().add("View/WorldMap.css");

                stage.setScene(scene);
                stage.show();
            }
        }
        }catch(NullPointerException | IOException ignored){}
    }

    public void errorChange(String text){
        errorText.setText(text);
        msgError.setVisible(true);
        FadeTransition ft = new FadeTransition(Duration.seconds(6), msgError);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.play();
    }

    public void onQuizClick(){
        try{
            String selectedTopic = TopicList.getSelectionModel().getSelectedItem();
            if (topics.contains(selectedTopic)) {
                if (Logic.checkSufficientQuestions(selectedTopic)){
                    SceneLoader.getInstance().LoadQuizMCTF(selectedTopic, quizButton, 0);
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
            errorChange("There are currently no questions for this quiz!");
        }
    }

    public void onReadClick() throws IOException, SQLException {
        try {
            QuizStorage.getInstance().setTopic(TopicList.getSelectionModel().getSelectedItem());
            String selectedItem = QuizStorage.getInstance().getTopic();
            if (topics.contains(selectedItem)) {
                SceneLoader.getInstance().LoadReadScenes(selectedItem,readButton);
            }
        }catch(NullPointerException ex){
            errorChange("You must choose a topic to read about!");
        }
    }

    public void onLogoutClick() throws IOException {
        SceneLoader.getInstance().LoadLogScene(logoutButton);
    }

    public void onEditClick() throws IOException, SQLException {
        try {
            String selectedItem = TopicList.getSelectionModel().getSelectedItem();
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
            errorChange("Something went wrong when trying to load edit scene!");
        }
    }

    public void onEditQuestion(){
        try{
            String selectedItem = TopicList.getSelectionModel().getSelectedItem();
            if (topics.contains(selectedItem)) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/QuizEditor.fxml"));
                Stage stage = (Stage) logoutButton.getScene().getWindow();
                Scene scene = new Scene(loader.load());
                scene.getStylesheets().add("View/Style.css");
                QuizEditorController quizEditorController = loader.getController();
                quizEditorController.setText(selectedItem);

                stage.setScene(scene);
                stage.show();
        } } catch (IOException e) {
            errorChange("Something went wrong when trying to load edit scene!");
        }
    }

    public void onAddCourse(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/AddCourse.fxml"));
            Stage stage = (Stage) addCourseButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add("View/Style.css");
            AddCourseController addCourseController = loader.getController();
            addCourseController.showElements(false);

            stage.setScene(scene);
            stage.show();
        } catch (IOException ignored) {}
    }

    public void onAboutOpen(){
        paneAbout.setVisible(true);
    }

    public void onAboutClose(){
        paneAbout.setVisible(false);
    }

}
