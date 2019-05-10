package UI;

import domain.Activity;
import domain.Controller;
import domain.users.Citizen;
import java.io.File;
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
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
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
    private MenuButton weekdayDropdownMenu;
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
        ObservableList<Citizen> obsCit = FXCollections.observableArrayList(pl.getiController().getCurrentSosu().getCitizens());
        borgerList.setItems(obsCit);

        mandagMenuButton.setUserData(0);
        tirsdagMenuButton.setUserData(1);
        onsdagMenuButton.setUserData(2);
        torsdagMenuButton.setUserData(3);
        fredagMenuButton.setUserData(4);
        lørdagMenuButton.setUserData(5);
        søndagMenuButton.setUserData(6);

    }

    @FXML
    private void addActivityButtonHandle(ActionEvent event) {
        String name = activityNameField.getText();
        String description = descriptionArea.getText();
        int sTime = Integer.parseInt(startTime.getText());
        int eTime = Integer.parseInt(endTime.getText());
        int day = (int) dagToggle.getSelectedToggle().getUserData();
        
        //Use controller method instead
        //Activity activity = new Activity(name, description, sTime, eTime, path, day);
        //borgerList.getSelectionModel().getSelectedItem().getSchedule().addActivity(activity);
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

    @FXML
    private void changeNameMandag(ActionEvent event) {
        weekdayDropdownMenu.setText("Mandag");
    }

    @FXML
    private void changeNameTirsdag(ActionEvent event) {
        weekdayDropdownMenu.setText("Tirsdag");
    }

    @FXML
    private void changeNameOnsdag(ActionEvent event) {
        weekdayDropdownMenu.setText("Onsdag");
    }

    @FXML
    private void changeNameTorsdag(ActionEvent event) {
        weekdayDropdownMenu.setText("Torsdag");
    }

    @FXML
    private void changeNameFredag(ActionEvent event) {
        weekdayDropdownMenu.setText("Fredag");
    }

    @FXML
    private void changeNameLørdag(ActionEvent event) {
        weekdayDropdownMenu.setText("Lørdag");
    }

    @FXML
    private void changeNameSøndag(ActionEvent event) {
        weekdayDropdownMenu.setText("Søndag");
    }

}
