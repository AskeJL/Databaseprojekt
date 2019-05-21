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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SOSUActivitiesController implements Initializable {

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
    private Button removeActivityBtn;
    @FXML
    private Button createActivityBtn;
    @FXML
    private TextArea descriptionTa;
    @FXML
    private Button goBackBtn;
    @FXML
    private Button printActivityBtn;
    @FXML
    private Label activityTitelLbl;
    @FXML
    private Label startTimeLabel;
    @FXML
    private Label endTimeLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pl = Planlægningsværktøj.getInstance();
        dayLabel.setText(pl.getDayArray()[pl.getCurrentDay()]);
        chosenDay = pl.getCurrentDay();
        System.out.println(chosenDay);
        obsAct = FXCollections.observableArrayList(pl.getiController().getCurrentCitizen().getSchedule().getActivityNamesOfday(chosenDay));
        activitiesLv.setItems(obsAct);
    }

    @FXML
    private void goBackButtonHandle(ActionEvent event) {
        pl.changeScene("Schedule.fxml");
    }

    @FXML
    private void removeActivityBtnHandle(ActionEvent event) {
        String selectedName = activitiesLv.getSelectionModel().getSelectedItem();
        UUID activityId = pl.getiController().getCurrentCitizen().getSchedule().getActivityId(selectedName);
        pl.getiController().deleteActivity(activityId);
        pl.getiController().getCurrentCitizen().getSchedule().removeActivity(activityId);
        pictogramIv.setImage(null);
        startTimeLabel.setText("");
        endTimeLabel.setText("");
        descriptionTa.setText("");
        activityTitelLbl.setText("");
        obsAct = FXCollections.observableArrayList(pl.getiController().getCurrentCitizen().getSchedule().getActivityNamesOfday(chosenDay));
        activitiesLv.setItems(obsAct);
    }

    @FXML
    private void createActivityBtnHandle(ActionEvent event) {
        pl.changeScene("CreateActivity2.fxml");
    }

    @FXML
    private void printActivityButtonHandle(ActionEvent event) {
    }

    @FXML
    private void lvClickedHandler(MouseEvent event) {

        if (activitiesLv.getSelectionModel().getSelectedItem() != null) {
            String selectedName = activitiesLv.getSelectionModel().getSelectedItem();
            UUID activityId = pl.getiController().getCurrentCitizen().getSchedule().getActivityId(selectedName);
            if (!"NoPicture".equals(pl.getiController().getPictogramPath(activityId))) {
                Image image = new Image(new File(pl.getiController().getPictogramPath(activityId)).toURI().toString());
                pictogramIv.setImage(image);
            } else {
                pictogramIv.setImage(null);
            }
            startTimeLabel.setText((String.valueOf(pl.getiController().getActivityStartTime(activityId))));
            endTimeLabel.setText((String.valueOf(pl.getiController().getActivityEndTime(activityId))));
            descriptionTa.setText(pl.getiController().getActivityDescription(activityId));
            activityTitelLbl.setText(pl.getiController().getActivityName(activityId));
        }
    }

}
