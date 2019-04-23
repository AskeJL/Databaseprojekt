package UI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class SOSUMainController implements Initializable {
    Planlægningsværktøj pl = Planlægningsværktøj.getPVInstance();
    @FXML
    private Label displayName;
    @FXML
    private ListView<?> citizenLv;
    @FXML
    private TextField search;
    @FXML
    private Button seeCitizensBtn;
    @FXML
    private Button seeScheduleBtn;
    @FXML
    private Button logOffBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void seeCitizensBtnHandle(ActionEvent event) {
    }

    @FXML
    private void seeScheduleBtnHandle(ActionEvent event) {
        pl.changeScene("Schedule.fxml");
    }

    @FXML
    private void logOffBtnHandle(ActionEvent event) {
        pl.changeScene("Login.fxml");
    }
    
}
