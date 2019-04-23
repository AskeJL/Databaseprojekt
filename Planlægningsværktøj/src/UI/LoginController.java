package UI;

import interfaces.IController;
import java.io.IOException;
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
import javafx.scene.input.KeyEvent;

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
        if (controller.authenticate(userName.getText(), passWord.getText())) {
           
        }


//        if (userName.getText().equals("LarsLort") && passWord.getText().equals("1234")) {
//            pl.changeScene("BorgerSchedule.fxml");
//        } else if (userName.getText().equals("DitteSørensen") && passWord.getText().equals("kat123")) {
//            pl.changeScene("SOSUMain.fxml");
//        } else {
//            loginFail.setText("Ugyldigt kombination af Brugernavn og Kodeord!");
//        }
    }
}
