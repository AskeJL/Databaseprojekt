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
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import java.util.UUID;

public class LoginController implements Initializable {

    Planlægningsværktøj pl;
    IController controller;

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
    @FXML
    private ToggleGroup userType;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pl = Planlægningsværktøj.getInstance();
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
        //SOSU login
        if (type == 1) {
            UUID id = controller.retrieveSosuId(userName.getText());
            controller.setCurrentSosu(id);
            pl.changeScene("SOSUMain.fxml");
        } //Citizen login
        else if (type == 2) {
            UUID id = controller.retrieveCitizenID(userName.getText());
            controller.setCurrentCitizen(id, userName.getText());
            System.out.println(controller.getCurrentCitizen().getSchedule().toString());
            pl.changeScene("BorgerSchedule.fxml");
        } else if (type == -1) {
            loginFail.setText("Ugyldig login.");
        }
    }
}
