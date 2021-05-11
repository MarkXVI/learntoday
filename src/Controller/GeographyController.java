package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GeographyController {

    @FXML
    Button btn = new Button();


    public void onButtonPressed(ActionEvent actionEvent) {
        btn = (Button) actionEvent.getSource();
        System.out.println(btn.getId());
    }
}
