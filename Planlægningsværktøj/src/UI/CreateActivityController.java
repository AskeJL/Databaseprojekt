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
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;

public class CreateActivityController implements Initializable {

    private Planlægningsværktøj pl;
    private String path = "NoPicture";
    private ObservableList<UUID> obsUUID;

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
    private ListView<String> borgerList;
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
    @FXML
    private Label confirmationLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pl = Planlægningsværktøj.getInstance();

        obsUUID = FXCollections.observableArrayList();
        for (int i = 0; i < pl.getiController().getCurrentSosu().getCitizens().size(); i++) {
            obsUUID.add(pl.getiController().getCurrentSosu().getCitizens().get(i).getId());
        }
        ObservableList<String> obsString = FXCollections.observableArrayList();
        for (UUID i : obsUUID) {
            obsString.add(pl.getiController().retrieveCitizenName(i));
        }

        borgerList.setItems(obsString);

        mandagMenuButton.setUserData(1);
        tirsdagMenuButton.setUserData(2);
        onsdagMenuButton.setUserData(3);
        torsdagMenuButton.setUserData(4);
        fredagMenuButton.setUserData(5);
        lørdagMenuButton.setUserData(6);
        søndagMenuButton.setUserData(7);

    }

    @FXML
    private void addActivityButtonHandler(ActionEvent event) {
        UUID citID = obsUUID.get(borgerList.getSelectionModel().getSelectedIndex());
        String username = pl.getiController().retrieveCitizenUsername(obsUUID.get(borgerList.getSelectionModel().getSelectedIndex()));
        pl.getiController().setCurrentCitizen(citID, username);

        String name = activityNameField.getText();
        String description = descriptionArea.getText();
        int sTime = Integer.parseInt(startTime.getText());
        int eTime = Integer.parseInt(endTime.getText());
        int day = (int) dagToggle.getSelectedToggle().getUserData();

        pl.getiController().getCurrentCitizen().getSchedule().addActivity(name, description, sTime, eTime, day, path);
        UUID activityUUID = pl.getiController().getCurrentCitizen().getSchedule().getActivityId(name);

        if (pl.getiController().storeActivity(activityUUID, citID, name, description, sTime, eTime, day, path)) {
            confirmationLabel.setText("Aktiviteten blev tilføjet!");
        } else {
            confirmationLabel.setText("Noget gik galt.");
        }

    }

    @FXML
    private void goBackButtonHandle(ActionEvent event) {
        pl.changeScene("SosuMainPage.fxml");
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
