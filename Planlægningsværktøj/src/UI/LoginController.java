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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class LoginController implements Initializable {

    Planlægningsværktøj pl = Planlægningsværktøj.getInstance();
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
        controller = pl.getiController();

    }

    @FXML
    private void handleLoginBtn(ActionEvent event) {
        RadioButton rb = (RadioButton) userType.getSelectedToggle();
        if (rb.getText().equals("SOSU") && controller.authenticate(userName.getText(), passWord.getText())) {
            pl.changeScene("SOSUMain.fxml");
        } else if (rb.getText().equals("Borger") && controller.authenticate(userName.getText(), passWord.getText())) {
            pl.changeScene("BorgerSchedule.fxml");
        } else {
            loginFail.setText("Ugyldigt kombination af Brugernavn og Kodeord!");
        }
    }
}
