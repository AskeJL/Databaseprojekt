package UI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author joakim
 */
public class ActivitiesController implements Initializable {

    @FXML
    private Label dayLabel;
    @FXML
    private ListView<?> activitiesLv;
    @FXML
    private ImageView pictogramIv;
    @FXML
    private TextArea pictogramTa;
    @FXML
    private Button removeActivityBtn;
    @FXML
    private Button createActivityBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void removeActivityBtnHandle(ActionEvent event) {
    }

    @FXML
    private void createActivityBtnHandle(ActionEvent event) {
    }
    
}
