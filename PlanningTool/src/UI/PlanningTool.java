package UI;

import domain.Controller;
import interfaces.IController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PlanningTool extends Application {

    private static PlanningTool pl = null;

    public PlanningTool() {
    }

    public static Stage stage;
    private final IController iController = new Controller();
    private int currentDay;
    private Scene scene;
    private final String[] dayArray = {"", "Mandag", "Tirsdag", "Onsdag", "Torsdag", "Fredag", "Lørdag", "Søndag"};

    public IController getiController() {
        return iController;
    }

    public static PlanningTool getInstance() {
        if (pl == null) {
            pl = new PlanningTool();
        }
        return pl;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

        scene = new Scene(root);

        scene.getStylesheets().add(PlanningTool.class.getResource("StyleSheet.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
        this.stage = stage;
    }

    public void changeScene(String s) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(s));
            Parent root1 = (Parent) loader.load();
            Stage stage = PlanningTool.stage;
            scene = new Scene(root1);
            scene.getStylesheets().add(PlanningTool.class.getResource("StyleSheet.css").toExternalForm());
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

    public String[] getDayArray() {
        return dayArray;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
