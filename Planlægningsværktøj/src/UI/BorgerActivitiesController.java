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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

public class BorgerActivitiesController implements Initializable {

    Planlægningsværktøj pl;
    @FXML
    private Label dayLabel;
    @FXML
    private ListView<String> activitiesLv;
    @FXML
    private ImageView pictogramIv;

    int chosenDay;

    ObservableList<String> obsAct;
    @FXML
    private TextArea pictogramTa;
    @FXML
    private Label startTimeLabel;
    @FXML
    private Label endTimeLabel;

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
        obsAct = FXCollections.observableArrayList(translateAcitivtyIds(pl.getiController().retrieveCitizenActivityIdsForGivenDay(pl.getiController().getCurrentCitizen().getId(), chosenDay)));
        activitiesLv.setItems(obsAct);
        
    }

    @FXML
    private void goBackButtonHandle(ActionEvent event) {
        pl.changeScene("BorgerSchedule.fxml");
    }

    public ArrayList<String> translateAcitivtyIds(UUID[] ids) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (UUID id : ids) {
            arrayList.add(pl.getiController().getActivityName(pl.getiController().getCurrentCitizen().getId(), id));
        }
        System.out.println(arrayList);
        return arrayList;
    }

}
