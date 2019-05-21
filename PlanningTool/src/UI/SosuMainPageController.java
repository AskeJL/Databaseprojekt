package UI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SosuMainPageController implements Initializable {

    private PlanningTool pl;

    @FXML
    private Label nameLabel;
    @FXML
    private Button seeCitizensBtn;
    @FXML
    private Button createActivityBtn;
    @FXML
    private Button logOffBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pl = PlanningTool.getInstance();
        nameLabel.setText("Bruger: " + pl.getiController().getCurrentSosu().getName());
    }

    @FXML
    private void seeCitizensBtnHandler(ActionEvent event) {
        pl.changeScene("SOSUMain.fxml");
    }

    @FXML
    private void createActivityBtnHandler(ActionEvent event) {
        pl.changeScene("CreateActivity.fxml");
    }

    @FXML
    private void logOffBtnHandler(ActionEvent event) {
        pl.changeScene("Login.fxml");
    }

}
