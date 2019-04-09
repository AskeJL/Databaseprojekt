package domain;

import domain.users.Citizen;
import java.util.ArrayList;
import java.util.Date;
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
        controller.tempList.add(lars);
        controller.addActivity(lars, "onani", "at onanere", 800, 1200, null, 6);
        System.out.println(controller.tempList.get(0).getSchedule().getSchedule()[6]);
    }
    //yeah
}
