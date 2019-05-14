package UI;

import domain.users.Citizen;
import java.net.URL;
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

    Planlægningsværktøj pl;
    @FXML
    private Label displayName;
    @FXML
    private ListView<Citizen> citizenLv;
    @FXML
    private Button seeScheduleBtn;
    @FXML
    private TextField searchField;
    @FXML
    private Button createActivityBtn;

    ObservableList<Citizen> obsCit = null;

    Planlægningsværktøj pv;
    @FXML
    private Button updateButton;
    @FXML
    private Button backBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pl = Planlægningsværktøj.getInstance();
        obsCit = FXCollections.observableArrayList(pl.getiController().getCurrentSosu().getCitizens());
        citizenLv.setItems(obsCit);
    }

    @FXML
    private void seeScheduleBtnHandle(ActionEvent event) {
        pl.changeScene("Schedule.fxml");
    }

    @FXML
    private void updateBtnHandler(ActionEvent event) {
        citizenLv.setItems(obsCit);
    }

    @FXML
    private void createActivityBtnHandler(ActionEvent event) {
        pl.changeScene("CreateActivity2.fxml");
    }

    @FXML
    private void backBtnHandler(ActionEvent event) {
        pl.changeScene("SosuMainPage.fxml");
    }

}
