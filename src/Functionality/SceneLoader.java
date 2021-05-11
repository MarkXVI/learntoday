package Functionality;

import Controller.QuizController;
import Controller.ReadController;
import Model.ConnectionStorage;
import Model.DBConnection;
import Model.QuizStorage;
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

    public SceneLoader() throws SQLException {}

    public void LoadMainMenu(Button btn) throws IOException {
        QuizStorage.getInstance().resetPoints();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/MainMenu.fxml"));
        Stage stage = (Stage) btn.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add("View/Style.css");
        stage.setScene(scene);
        stage.show();
    }

    public void LoadQuizMCTF(String selectedTopic, Button btn, int count) throws SQLException, IOException {
        QuizStorage.getInstance().add_questions(selectedTopic);
        String URL;
        QuizStorage.getInstance().QuizShuffle();
        if(database.getQuestionType(QuizStorage.getInstance().get_questionIDs().get(count)).equals("MC")) { URL = "../View/QuizMultipleChoice.fxml";} else {URL = "../View/QuizTrueOrFalse.fxml";}
        FXMLLoader loader = new FXMLLoader(getClass().getResource(URL));
        Stage stage = (Stage) btn.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add("View/Style.css");
        QuizController quizController = loader.getController();
        quizController.setTitle(selectedTopic);

        stage.setScene(scene);
        stage.show();
    }

    public void LoadReadScenes(String selectedItem, Button readButton) throws SQLException, IOException {
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

    public void LoadLogScene(Button logoutButton) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/LoginScreen.fxml")); // ↓↓↓↓ Switches scene to login screen.
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add("View/Style.css");
        stage.setScene(scene);
        stage.show();
    }
}
