package UI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ScheduleController implements Initializable {

    Planlægningsværktøj pl;

    @FXML
    private Label displayName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pl = Planlægningsværktøj.getInstance();
    }

    @FXML
    private void mondayBtnHandle(ActionEvent event) {
        pl.changeScene("SOSUActivities.fxml");
    }

    @FXML
    private void tuesdayBtnHandle(ActionEvent event) {
        pl.changeScene("SOSUActivities.fxml");
    }

    @FXML
    private void wednesdayBtnHandle(ActionEvent event) {
        pl.changeScene("SOSUActivities.fxml");
    }

    @FXML
    private void thursdayBtnHandle(ActionEvent event) {
        pl.changeScene("SOSUActivities.fxml");
    }

    @FXML
    private void fridayBtnHandle(ActionEvent event) {
        pl.changeScene("SOSUActivities.fxml");
    }

    @FXML
    private void saturdayBtnHandle(ActionEvent event) {
        pl.changeScene("SOSUActivities.fxml");
    }

    @FXML
    private void sundayBtnHandle(ActionEvent event) {
        pl.changeScene("SOSUActivities.fxml");
    }

    @FXML
    private void goBackButtonHandle(ActionEvent event) {
        pl.changeScene("SOSUMain.fxml");
    }

}
