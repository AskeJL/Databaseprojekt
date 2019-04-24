package UI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class CreateActivityController implements Initializable {

    Planlægningsværktøj pl = Planlægningsværktøj.getInstance();
    @FXML
    private ListView<?> timeLv;
    @FXML
    private ListView<?> activityLv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void addActivityButtonHandle(ActionEvent event) {
    }

    @FXML
    private void goBackButtonHandle(ActionEvent event) {
        pl.changeScene("SOSUActivities.fxml");
    }

}
