package UI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author askel
 */
public class CitizenMainPageController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Button logOffBtn;
    @FXML
    private Button seeScheduleBtn;
    @FXML
    private Button printBtn;
    
    Planlægningsværktøj pl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pl = Planlægningsværktøj.getInstance();
        nameLabel.setText(pl.getiController().getCurrentCitizen().getName());
    }    

    @FXML
    private void logOffBtnHandler(ActionEvent event) {
        pl.changeScene("Login.fxml");
    }

    @FXML
    private void seeScheduleBtnHandler(ActionEvent event) {
        pl.changeScene("BorgerSchedule.fxml");
    }

    @FXML
    private void printBtnHandler(ActionEvent event) {
        System.out.println("Ikke implementeret endnu.");
    }
    
}
