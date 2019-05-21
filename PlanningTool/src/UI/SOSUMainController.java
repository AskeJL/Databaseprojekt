package UI;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;
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

    private PlanningTool pl;
    private ObservableList<UUID> obsUUID;
    private ObservableList<String> obsString;

    @FXML
    private Label displayName;
    @FXML
    private ListView<String> citizenLv;
    @FXML
    private Button seeScheduleBtn;
    @FXML
    private TextField searchField;
    @FXML
    private Button createActivityBtn;
    @FXML
    private Button updateButton;
    @FXML
    private Button backBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pl = PlanningTool.getInstance();
        obsUUID = FXCollections.observableArrayList();
        for (int i = 0; i < pl.getiController().getCurrentSosu().getCitizens().size(); i++) {
            obsUUID.add(pl.getiController().getCurrentSosu().getCitizens().get(i).getId());
        }
        obsString = FXCollections.observableArrayList();
        for (UUID i : obsUUID) {
            obsString.add(pl.getiController().retrieveCitizenName(i));
        }
        citizenLv.setItems(obsString);
    }

    @FXML
    private void seeScheduleBtnHandle(ActionEvent event) {
        UUID citID = pl.getiController().retrieveCitizenID(citizenLv.getSelectionModel().getSelectedItem());
        String username = pl.getiController().retrieveCitizenUsername(citID);
        pl.getiController().setCurrentCitizen(citID, username);
        pl.changeScene("Schedule.fxml");
    }

    @FXML
    private void updateBtnHandler(ActionEvent event) {
        obsUUID.clear();
        obsString.clear();
        obsUUID = FXCollections.observableArrayList();
        for (int i = 0; i < pl.getiController().getCurrentSosu().getCitizens().size(); i++) {
            obsUUID.add(pl.getiController().getCurrentSosu().getCitizens().get(i).getId());
        }
        obsString = FXCollections.observableArrayList();
        for (UUID i : obsUUID) {
            obsString.add(pl.getiController().retrieveCitizenName(i));
        }

        citizenLv.setItems(obsString);
    }

    @FXML
    private void createActivityBtnHandler(ActionEvent event) {
        UUID citID = obsUUID.get(citizenLv.getSelectionModel().getSelectedIndex());
        String username = pl.getiController().retrieveCitizenUsername(obsUUID.get(citizenLv.getSelectionModel().getSelectedIndex()));
        pl.getiController().setCurrentCitizen(citID, username);
        pl.changeScene("CreateActivity2.fxml");
    }

    @FXML
    private void backBtnHandler(ActionEvent event) {
        pl.changeScene("SosuMainPage.fxml");
    }

}
