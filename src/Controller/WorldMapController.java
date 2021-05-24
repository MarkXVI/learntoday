package Controller;

import Functionality.SceneLoader;
import Model.QuizStorage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class WorldMapController implements Initializable {

    @FXML
    Button btn = new Button();
    @FXML
    Button homeButton;
    @FXML
    Text Text;
    @FXML
    Button resultsButton;

    static ArrayList<String> Boundaries = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String urlString = url.toString();
        String currentScene = urlString.substring(urlString.length() - 17);
        QuizStorage.getInstance().resetPoints();
        if (currentScene.contains("Continents")) {
            QuizStorage.getInstance().setTopic("Continents");

            Boundaries.add("Europe");
            Boundaries.add("South America");
            Boundaries.add("North America");
            Boundaries.add("Africa");
            Boundaries.add("Asia");
            Boundaries.add("Oceania");
            Boundaries.add("Antarctica");
        } else if (currentScene.contains("Oceans")){
            QuizStorage.getInstance().setTopic("Oceans");

            Boundaries.add("The North Pacific Ocean");
            Boundaries.add("The South Pacific Ocean");
            Boundaries.add("The North Atlantic Ocean");
            Boundaries.add("The South Atlantic Ocean");
            Boundaries.add("The Indian Ocean");
            Boundaries.add("The Southern Ocean");
            Boundaries.add("The Arctic Ocean");
        }
        Collections.shuffle(Boundaries);
        QuizStorage.getInstance().addQuestions(Boundaries);
        nextCountry();
    }

    public void onButtonPressed(ActionEvent actionEvent) {
        btn = (Button) actionEvent.getSource();
        if(Boundaries.size() < 1){
            Text.setText(btn.getId());
        }else{
        if(btn.getId().equals(Boundaries.get(0))) {
            btn.setStyle(btn.getStyle() + "-fx-background-color: Green;");
            QuizStorage.getInstance().addPoint();
        }else{
            btn.setStyle(btn.getStyle() + "-fx-background-color: Red;");
        }
        Boundaries.remove(0);
        if (Boundaries.size() > 0) {
                nextCountry();
            }else{
                Text.setText("All continents completed!");
                resultsButton.setVisible(true);
            }
        }
    }

    public void onResultsClick() throws IOException {
        SceneLoader.getInstance().loadScoreScreen(resultsButton);
    }

    public void nextCountry(){
        Text.setText("Click on: " + Boundaries.get(0));
    }

    public void onHomeClick() throws IOException {
        SceneLoader.getInstance().loadMainMenu(homeButton);
    }


}
