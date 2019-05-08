package UI;

import domain.Controller;
import domain.users.Citizen;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class SOSUMainController implements Initializable {
    Planlægningsværktøj pl = Planlægningsværktøj.getInstance();
    @FXML
    private Label displayName;
    @FXML
    private ListView<Citizen> citizenLv;
    @FXML
    private Button seeScheduleBtn;
    @FXML
    private Button logOffBtn;
    @FXML
    private TextField searchField;
    @FXML
    private Button createActivityBtn;
    
    ObservableList<Citizen> obsCit = null;
    
    Planlægningsværktøj pv;
    @FXML
    private Button updateButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        obsCit = FXCollections.observableArrayList(pl.getiController().getCurrentSosu().getCitizens());
        citizenLv.setItems(obsCit);
    }    


    @FXML
    private void seeScheduleBtnHandle(ActionEvent event) {
        pl.changeScene("Schedule.fxml");
    }

    @FXML
    private void logOffBtnHandle(ActionEvent event) {
        pl.changeScene("Login.fxml");
    }

    @FXML
    private void updateBtnHandler(ActionEvent event) {
        citizenLv.setItems(obsCit);
    }

    @FXML
    private void createActivityBtnHandler(ActionEvent event) {
        pl.changeScene("CreateActivity.fxml");
    }
    
}
