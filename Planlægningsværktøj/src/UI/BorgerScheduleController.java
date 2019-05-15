package UI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import sun.security.pkcs11.P11TlsKeyMaterialGenerator;

public class BorgerScheduleController implements Initializable {

    Planlægningsværktøj pl;
    @FXML
    private Label displayName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pl = Planlægningsværktøj.getInstance();
        displayName.setText(pl.getiController().getCurrentCitizenName());
    }

    @FXML
    private void mondayBtnHandle(ActionEvent event) {
        pl.setCurrentDay(1);
        pl.changeScene("BorgerActivities.fxml");

    }

    @FXML
    private void tuesdayBtnHandle(ActionEvent event) {
        pl.setCurrentDay(2);
        pl.changeScene("BorgerActivities.fxml");
    }

    @FXML
    private void wednesdayBtnHandle(ActionEvent event) {
        pl.setCurrentDay(3);
        pl.changeScene("BorgerActivities.fxml");
    }

    @FXML
    private void thursdayBtnHandle(ActionEvent event) {
        pl.setCurrentDay(4);
        pl.changeScene("BorgerActivities.fxml");
    }

    @FXML
    private void fridayBtnHandle(ActionEvent event) {
        pl.setCurrentDay(5);
        pl.changeScene("BorgerActivities.fxml");
    }

    @FXML
    private void saturdayBtnHandle(ActionEvent event) {
        pl.setCurrentDay(6);
        pl.changeScene("BorgerActivities.fxml");
    }

    @FXML
    private void sundayBtnHandle(ActionEvent event) {
        pl.setCurrentDay(7);
        pl.changeScene("BorgerActivities.fxml");
    }

    @FXML
    private void logOffButtonHandle(ActionEvent event) {
        pl.changeScene("Login.fxml");
    }

}
