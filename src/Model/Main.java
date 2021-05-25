package Model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Objects;


public class Main extends Application {
    ConnectionStorage connection;
    @Override
    public void start(Stage primaryStage) {
        try {
            connection = ConnectionStorage.getInstance();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../View/LoginScreen.fxml")));

        primaryStage.setTitle("Learn2day");
        Scene scene = new Scene(root, 900, 720);
        scene.getStylesheets().add("View/Style.css");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
