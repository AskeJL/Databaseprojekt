package domain;

import domain.users.Citizen;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;

public class Controller {

    ArrayList<Citizen> tempList = new ArrayList<>();

    public void addActivity(Citizen citizen, String name, String description, int startTime, int endTime, Image pictogram, int day) {
        if (tempList.contains(citizen)) {
            citizen.getSchedule().addActivity(new Activity(name, description, startTime, endTime, pictogram), day);
        }
    }

    public static void main(String[] args) {
        //GG
        Controller controller = new Controller();
        Citizen lars = new Citizen("Lars Lort", "1234", new Date());
        Citizen anders = new Citizen("Anders And", "5678", new Date());
        Citizen katrine = new Citizen("Katrine", "3456", new Date());
        try (ObjectOutputStream outputStream = new ObjectOutputStream(
                new FileOutputStream("Logins.txt", true))) {

            outputStream.writeObject(lars);
            outputStream.writeObject(anders);
            outputStream.writeObject(katrine);

        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        controller.tempList.add(lars);
        controller.addActivity(lars, "onani", "at onanere", 800, 1200, null, 6);
        System.out.println(controller.tempList.get(0).getSchedule().getSchedule()[6]);
        //yeah7

        Login login = new Login();
        System.out.println(login.Authenticate("Anders And", "5678"));

    }
}
