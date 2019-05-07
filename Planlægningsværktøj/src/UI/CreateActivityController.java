package UI;

import domain.Activity;
import domain.Controller;
import domain.users.Citizen;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;

public class CreateActivityController implements Initializable {

    Planlægningsværktøj pl = Planlægningsværktøj.getInstance();
    @FXML
    private Button addActivityButton;
    @FXML
    private Button goBackButton;
    @FXML
    private TextField activityNameField;
    @FXML
    private TextField startTime;
    @FXML
    private TextField endTime;
    @FXML
    private ListView<Citizen> borgerList;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private Button addPicture;
    @FXML
    private SplitMenuButton weekdayDropdownMenu;
    @FXML
    private RadioMenuItem mandagMenuButton;
    @FXML
    private ToggleGroup dagToggle;
    @FXML
    private RadioMenuItem tirsdagMenuButton;
    @FXML
    private RadioMenuItem onsdagMenuButton;
    @FXML
    private RadioMenuItem torsdagMenuButton;
    @FXML
    private RadioMenuItem fredagMenuButton;
    @FXML
    private RadioMenuItem lørdagMenuButton;
    @FXML
    private RadioMenuItem søndagMenuButton;
    
    private String path;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Controller c = new Controller();
        ArrayList<Citizen> cit = new ArrayList<>();
        ObservableList<Citizen> obsCit = FXCollections.observableArrayList(cit);
        borgerList.setItems(obsCit);
        mandagMenuButton.getToggleGroup().setUserData(0);
        tirsdagMenuButton.getToggleGroup().setUserData(1);
        onsdagMenuButton.getToggleGroup().setUserData(2);
        torsdagMenuButton.getToggleGroup().setUserData(3);
        fredagMenuButton.getToggleGroup().setUserData(4);
        lørdagMenuButton.getToggleGroup().setUserData(5);
        søndagMenuButton.getToggleGroup().setUserData(6);

    }

    @FXML
    private void addActivityButtonHandle(ActionEvent event) {
        String name = activityNameField.getText();
        String description = descriptionArea.getText();
        int sTime = Integer.parseInt(startTime.getText());
        int eTime = Integer.parseInt(endTime.getText());
        int day =(int) dagToggle.getSelectedToggle().getUserData();
        Activity activity = new Activity(name, description, sTime, eTime, path, day);
        borgerList.getSelectionModel().getSelectedItem().getSchedule().addActivity(activity);
    }

    @FXML
    private void goBackButtonHandle(ActionEvent event) {
        pl.changeScene("SOSUActivities.fxml");
    }

    @FXML
    private void addPictureHandler(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        path = chooser.showOpenDialog(null).getAbsolutePath();
        
    }

}
