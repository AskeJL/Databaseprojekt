package UI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

public class BorgerActivitiesController implements Initializable {

    Planlægningsværktøj pl = Planlægningsværktøj.getPVInstance();
    @FXML
    private Label dayLabel;
    @FXML
    private ListView<?> activitiesLv;
    @FXML
    private ImageView pictogramIv;
    @FXML
    private TextArea pictogramTa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void goBackButtonHandle(ActionEvent event) {
        pl.changeScene("BorgerSchedule.fxml");
    }

}
