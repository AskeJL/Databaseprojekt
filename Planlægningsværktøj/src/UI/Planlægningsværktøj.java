package UI;

import domain.Controller;
import interfaces.IController;
import interfaces.IControllerDB;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Planlægningsværktøj extends Application {

    private static Planlægningsværktøj pl = new Planlægningsværktøj();

    public Planlægningsværktøj() {
    }

    public static Stage stage;
    private final IController iController = new Controller();
    private final IControllerDB iControllerDB = new Controller();

    public IController getiController() {
        return iController;
    }

    public static Planlægningsværktøj getInstance() {
        return pl;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

        Scene scene = new Scene(root);

        scene.getStylesheets().add(Planlægningsværktøj.class.getResource("StyleSheet.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
        this.stage = stage;
    }

    public void changeScene(String s) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(s));
            Parent root1 = (Parent) loader.load();
            Stage stage = Planlægningsværktøj.stage;
            Scene scene = new Scene(root1);
            scene.getStylesheets().add(Planlægningsværktøj.class.getResource("StyleSheet.css").toExternalForm());
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.out.println("Can't load window");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
