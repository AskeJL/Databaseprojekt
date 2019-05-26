package UI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ScheduleController implements Initializable {

    private PlanningTool pl;

    @FXML
    private Label displayName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pl = PlanningTool.getInstance();
        displayName.setText(pl.getiController().getCurrentCitizenName());
    }

    @FXML
    private void mondayBtnHandle(ActionEvent event) {
        pl.setCurrentDay(1);
        pl.changeScene("SOSUActivities.fxml");
    }

    @FXML
    private void tuesdayBtnHandle(ActionEvent event) {
        pl.setCurrentDay(2);
        pl.changeScene("SOSUActivities.fxml");
    }

    @FXML
    private void wednesdayBtnHandle(ActionEvent event) {
        pl.setCurrentDay(3);
        pl.changeScene("SOSUActivities.fxml");
    }

    @FXML
    private void thursdayBtnHandle(ActionEvent event) {
        pl.setCurrentDay(4);
        pl.changeScene("SOSUActivities.fxml");
    }

    @FXML
    private void fridayBtnHandle(ActionEvent event) {
        pl.setCurrentDay(5);
        pl.changeScene("SOSUActivities.fxml");
    }

    @FXML
    private void saturdayBtnHandle(ActionEvent event) {
        pl.setCurrentDay(6);
        pl.changeScene("SOSUActivities.fxml");
    }

    @FXML
    private void sundayBtnHandle(ActionEvent event) {
        pl.setCurrentDay(7);
        pl.changeScene("SOSUActivities.fxml");
    }

    @FXML
    private void goBackButtonHandle(ActionEvent event) {
        pl.changeScene("SOSUMain.fxml");
    }

}
