package Controller;

import Functionality.SceneLoader;
import Model.ConnectionStorage;
import Model.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.sql.SQLException;

public class LeaderboardsController {

    DBConnection database = ConnectionStorage.getInstance().getConnection();
    @FXML
    Button homeButton;
    @FXML
    Rectangle whiteRectangle;


    public LeaderboardsController() throws SQLException {}

    public void onHomeClick(ActionEvent actionEvent) throws IOException {
        SceneLoader.getInstance().LoadMainMenu(homeButton);
    }
}
