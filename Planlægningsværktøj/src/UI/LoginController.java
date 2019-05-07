package UI;

import interfaces.IController;
import interfaces.IControllerDB;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sun.java2d.loops.SurfaceType;

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
        pl.changeScene("CreateActivity.fxml");
}
}