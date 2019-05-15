package UI;

import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

public class SOSUActivitiesController implements Initializable {

    Planlægningsværktøj pl;
    @FXML
    private Label dayLabel;
    @FXML
    private ListView<String> activitiesLv;
    @FXML
    private ImageView pictogramIv;
    @FXML
    private TextArea pictogramTa;
    @FXML
    private Button removeActivityBtn;
    @FXML
    private Button createActivityBtn;
    
    int chosenDay;

    ObservableList<String> obsAct;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pl = Planlægningsværktøj.getInstance();
        dayLabel.setText(pl.dayArray[pl.getCurrentDay()]);
        chosenDay = pl.getCurrentDay();
        System.out.println(chosenDay);
        //TODO, show only the activities of a certain day
//        obsAct = FXCollections.observableArrayList(translateAcitivtyIds(pl.getiController().retrieveCitizenActivityIdsForGivenDay(pl.getiController().getCurrentCitizen().getId(), chosenDay)));
        activitiesLv.setItems(obsAct);
    }

    @FXML
    private void goBackButtonHandle(ActionEvent event) {
        pl.changeScene("Schedule.fxml");
    }

    @FXML
    private void removeActivityBtnHandle(ActionEvent event) {
    }

    @FXML
    private void createActivityBtnHandle(ActionEvent event) {
        pl.changeScene("CreateActivity2.fxml");
    }

    @FXML
    private void printActivityButtonHandle(ActionEvent event) {
    }
    
    public ArrayList<String> translateAcitivtyIds(UUID[] ids) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (UUID id : ids) {
//            arrayList.add(pl.getiController().getActivityName(pl.getiController().getCurrentCitizen().getId(), id));
        }
        System.out.println(arrayList);
        return arrayList;
    }

}
