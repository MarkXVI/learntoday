package Controller;

import Functionality.SceneLoader;
import Model.QuizStorage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class ContinentsController implements Initializable {

    @FXML
    Button btn = new Button();
    @FXML
    Button homeButton;
    @FXML
    Text continentText;
    @FXML
    Button resultsButton;

    static ArrayList<String> continents = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        QuizStorage.getInstance().resetPoints();
        QuizStorage.getInstance().setTopic("Continents");

        continents.add("Europe");
        continents.add("South America");
        continents.add("North America");
        continents.add("Africa");
        continents.add("Asia");
        continents.add("Oceania");
        continents.add("Antarctica");
        Collections.shuffle(continents);
        QuizStorage.getInstance().addQuestions(continents);
        nextCountry();
    }

    public void onButtonPressed(ActionEvent actionEvent) {
        btn = (Button) actionEvent.getSource();
        if(continents.size() < 1){
            continentText.setText(btn.getId());
        }else{
        if(btn.getId().equals(continents.get(0))) {
            QuizStorage.getInstance().addPoint();
            continents.remove(0);
            if (continents.size() > 0) {
                nextCountry();
            }else{
                continentText.setText("All continents completed!");
                resultsButton.setVisible(true);
            }
        }
        }
    }

    public void onResultsClick() throws IOException {
        SceneLoader.getInstance().loadScoreScreen(resultsButton);
    }

    public void nextCountry(){
        continentText.setText("Click on: " + continents.get(0));
    }

    public void onHomeClick() throws IOException {
        SceneLoader.getInstance().loadMainMenu(homeButton);
    }


}
