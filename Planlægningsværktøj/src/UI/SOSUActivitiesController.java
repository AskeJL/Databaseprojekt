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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SOSUActivitiesController implements Initializable {

    Planlægningsværktøj pl;
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
    
    int chosenDay;

    ObservableList<String> obsAct;
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

    @FXML
    private void lvClickedHandler(MouseEvent event) {
        
        if(activitiesLv.getSelectionModel().getSelectedItem() != null){
            Image image = new Image("activity image path");
            pictogramIv.setImage(image);
            activityTitelLbl.setText("ListView selection navn");
            descriptionTa.setText("list view selection description");
            
        }
    }

}
