package domain;

import domain.users.Citizen;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import interfaces.IController;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.UUID;

public class Controller implements IController {

    ArrayList<Citizen> tempList = new ArrayList<>();

    public void addActivity(Citizen citizen, String name, String description, int startTime, int endTime, String pictogram, int day) {
        if (tempList.contains(citizen)) {
            citizen.getSchedule().addActivity(new Activity(name, description, startTime, endTime, pictogram, day));
        }
    }

    public static void main(String[] args) {
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
        controller.addActivity(lars, "onanifirst", "at onanere", 800, 1200, null, 5);
        controller.addActivity(lars, "onanisecond", "at onanere", 700, 1200, null, 6);
        System.out.println(controller.tempList.get(0).getSchedule().toString());

        Login login = new Login();
        System.out.println(login.Authenticate("Anders And", "5678"));

    }

    private Citizen getCitizen(UUID userID) {
        //TODO update for database
        for (Citizen citizen : tempList) {
            if (citizen.getId().equals(userID)) {
                return citizen;
            }
        }
        return null;
    }

    private Activity getActivity(UUID userID, UUID activityID) {
        return getCitizen(userID).getSchedule().getActivity(activityID);
    }

    @Override
    public ArrayList<UUID> getSchedule(UUID userID) {
        ArrayList<UUID> returnSchedule = new ArrayList<>();
        ArrayList<Activity> originalSchedule = getCitizen(userID).getSchedule().getSchedule();
        for (Activity activity : originalSchedule) {
            returnSchedule.add(activity.getActivityID());
        }
        return returnSchedule;
    }

    @Override
    public String getActivityName(UUID userID, UUID activityID) {
        return getActivity(userID, activityID).getName();
    }

    @Override
    public String getActivityDescription(UUID userID, UUID activityID) {
        return getActivity(userID, activityID).getDescription();
    }

    @Override
    public int getActivityStartTime(UUID userID, UUID activityID) {
        return getActivity(userID, activityID).getStartTime();
    }

    @Override
    public int getActivityEndTime(UUID userID, UUID activityID) {
        return getActivity(userID, activityID).getEndTime();
    }

    @Override
    public String getPictogramPath(UUID userID, UUID activityID) {
        return getActivity(userID, activityID).getPictogramPath();
    }
}
