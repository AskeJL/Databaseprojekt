package UI;

import domain.Controller;
import interfaces.IController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Planlægningsværktøj extends Application {

    private static Planlægningsværktøj pl = null;

    public Planlægningsværktøj() {
    }

    public static Stage stage;
    private final IController iController = new Controller();
    int currentDay;
    Scene scene;

    public IController getiController() {
        return iController;
    }

    public static Planlægningsværktøj getInstance() {
        if(pl == null){
            pl = new Planlægningsværktøj();
        }
        return pl;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

        scene = new Scene(root);

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
            scene = new Scene(root1);
            scene.getStylesheets().add(Planlægningsværktøj.class.getResource("StyleSheet.css").toExternalForm());
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.out.println("Can't load window" + e);
        }
    }

    public int getCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(int currentDay) {
        this.currentDay = currentDay;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
