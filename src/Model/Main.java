package Model;

import Controller.MenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class Main extends Application {
    ConnectionStorage connection;
    @Override
    public void start(Stage primaryStage) throws Exception {
        connection = ConnectionStorage.getInstance();
        Parent root = FXMLLoader.load(getClass().getResource("../View/LoginScreen.fxml"));
        primaryStage.setTitle("Learn2day");
        Scene scene = new Scene(root, 900, 720);
        scene.getStylesheets().add("View/Style.css");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
    }

    @Override
    public void stop() {
        connection.close_Connection();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
