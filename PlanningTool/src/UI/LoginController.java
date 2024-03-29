package UI;

import interfaces.IController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import java.util.UUID;

public class LoginController implements Initializable {

    private PlanningTool pl;
    private IController controller;

    @FXML
    private Label label;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField passWord;
    @FXML
    private Text loginFail;
    @FXML
    private Button loginBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pl = PlanningTool.getInstance();
        controller = pl.getiController();

        passWord.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                handleLoginBtn(new ActionEvent());
            }
        });
    }

    @FXML
    private void handleLoginBtn(ActionEvent event) {
        int type = controller.authenticate(userName.getText(), passWord.getText());
        switch (type) {
            case 1: {
                //SOSU login
                UUID id = controller.retrieveSosuId(userName.getText());
                controller.setCurrentSosu(id);
                pl.changeScene("SosuMainPage.fxml");
                break;
            }
            case 2: {
                //Citizen login
                UUID id = controller.retrieveCitizenID(userName.getText());
                controller.setCurrentCitizen(id, userName.getText());
                pl.changeScene("CitizenMainPage.fxml");
                break;
            }
            case -1:
                loginFail.setText("Ugyldigt login.");
                break;
            default:
                break;
        }
    }
}
