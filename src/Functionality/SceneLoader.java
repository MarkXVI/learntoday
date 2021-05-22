package Functionality;

import Controller.ManageTopicsController;
import Controller.QuizController;
import Controller.ReadController;
import Model.ConnectionStorage;
import Model.DBConnection;
import Model.QuizStorage;
import Model.UserStorage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class SceneLoader {
    private static SceneLoader sceneLoader;
    static {
        try {
            sceneLoader = new SceneLoader();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
    public static SceneLoader getInstance() { return sceneLoader;}

    DBConnection database = ConnectionStorage.getInstance().getConnection();

    UserStorage userStorage = UserStorage.getInstance();
    User user = userStorage.currentUser();

    public SceneLoader() throws SQLException {}

    public void loadManage(Button btn) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/ManageCourses.fxml"));
        Stage stage = (Stage) btn.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add("View/Style.css");

        stage.setScene(scene);
        stage.show();
    }

    public void loadMainMenu(Button btn) throws IOException {
        QuizStorage.getInstance().resetPoints();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/MainMenu.fxml"));
        Stage stage = (Stage) btn.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add("View/Style.css");
        stage.setScene(scene);
        stage.show();
    }

    public void loadLeaderboard(Button btn) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/Leaderboards.fxml"));
        Stage stage = (Stage) btn.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add("View/Style.css");

        stage.setScene(scene);
        stage.show();
    }

    public void loadQuizMCTF(String selectedTopic, Button btn, int count) throws SQLException, IOException {
        String URL;
        if(database.getQuestionType(QuizStorage.getInstance().getQuestionIDs().get(count)).equals("MC")) { URL = "../View/QuizMultipleChoice.fxml";} else {URL = "../View/QuizTrueOrFalse.fxml";}
        FXMLLoader loader = new FXMLLoader(getClass().getResource(URL));
        Stage stage = (Stage) btn.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add("View/Style.css");
        QuizController quizController = loader.getController();
        quizController.setTitle(selectedTopic);

        stage.setScene(scene);
        stage.show();
        QuizStorage.getInstance().setTopic(selectedTopic);
    }

    public void loadReadScenes(String selectedItem, Button readButton) throws SQLException, IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/ReadScreen.fxml"));
        Stage stage = (Stage) readButton.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add("View/Style.css");

        ReadController readController = loader.getController();
        readController.setText(database.getText(selectedItem), selectedItem);

        stage.setScene(scene);
        stage.show();
    }

    public void loadLogScene(Button logoutButton) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/LoginScreen.fxml")); // ↓↓↓↓ Switches scene to login screen.
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add("View/Style.css");

        stage.setScene(scene);
        stage.show();
    }
}
