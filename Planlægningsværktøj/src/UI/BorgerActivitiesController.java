package UI;

import java.io.File;
import java.net.URL;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class BorgerActivitiesController implements Initializable {

    private Planlægningsværktøj pl;
    private int chosenDay;
    private ObservableList<String> obsAct;

    @FXML
    private Label dayLabel;
    @FXML
    private ListView<String> activitiesLv;
    @FXML
    private ImageView pictogramIv;

    @FXML
    private TextArea pictogramTa;
    @FXML
    private Label startTimeLabel;
    @FXML
    private Label endTimeLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pl = Planlægningsværktøj.getInstance();
        dayLabel.setText(pl.dayArray[pl.getCurrentDay()]);
        chosenDay = pl.getCurrentDay();
        obsAct = FXCollections.observableArrayList(pl.getiController().getCurrentCitizen().getSchedule().getActivityNamesOfday(chosenDay));
        activitiesLv.setItems(obsAct);
    }

    @FXML
    private void goBackButtonHandle(ActionEvent event) {
        pl.changeScene("BorgerSchedule.fxml");
    }

    @FXML
    private void handleLvMouseClickEvent(MouseEvent event) {
        if (activitiesLv.getSelectionModel().getSelectedItem() != null) {
            String selectedName = activitiesLv.getSelectionModel().getSelectedItem();
            UUID activityId = pl.getiController().getCurrentCitizen().getSchedule().getActivityId(selectedName);
            startTimeLabel.setText((String.valueOf(pl.getiController().getActivityStartTime(activityId))));
            endTimeLabel.setText((String.valueOf(pl.getiController().getActivityEndTime(activityId))));
            pictogramTa.setText(pl.getiController().getActivityDescription(activityId));
            if (!"NoPicture".equals(pl.getiController().getPictogramPath(activityId))) {
                pictogramIv.setImage(new Image(new File(pl.getiController().getPictogramPath(activityId)).toURI().toString()));
            } else {
                pictogramIv.setImage(null);
            }
        }
    }
}
