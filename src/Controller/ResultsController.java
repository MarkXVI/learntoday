package Controller;

import Functionality.SceneLoader;
import Functionality.User;
import Model.ConnectionStorage;
import Model.DBConnection;
import Model.QuizStorage;
import Model.UserStorage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class ResultsController implements Initializable {
    @FXML
    Text totalScore;
    @FXML
    Text perfectScore;
    @FXML
    Button uploadButton;
    @FXML
    Button finishButton;

    DBConnection database = ConnectionStorage.getInstance().getConnection();

    int points = QuizStorage.getInstance().getPoints();
    int questions = QuizStorage.getInstance().getQuestions();
    User user = UserStorage.getInstance().currentUser();

    public ResultsController() throws SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        perfectScore.setVisible(points == questions);
        totalScore.setText(points + "/" + questions);
    }

    public void onFinishClick() throws IOException {
        SceneLoader.getInstance().loadMainMenu(finishButton);

    }

    public void onUploadClick() throws SQLException {
        String topic = QuizStorage.getInstance().getTopic();
        Object oldScore = database.getUserScore(user.getUsername(), topic);
        if(oldScore == null){
            database.addUserScore(user.getUsername(), topic, points);
        }else if((int) oldScore < points){
            database.updateUserScore(user.getUsername(), topic, points );
        }

    }
}
