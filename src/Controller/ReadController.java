package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ReadController implements Initializable {
    @FXML
    Button ttsButton;
    @FXML
    private TextArea infoText;
    @FXML
    private Text topicText;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void set_Text(String text, String topic){
        infoText.setText(text);
        topicText.setText(topic);
    }
}
