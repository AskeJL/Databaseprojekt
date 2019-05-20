package domain;

import database.DBController;
import domain.users.Citizen;
import domain.users.SOSU;
import interfaces.IController;
import interfaces.IControllerDB;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

public class Controller implements IController {

    //Main til test af kode
    public static void main(String[] args) {
        Controller controller = new Controller();
//        UUID[] array = controller.retrieveCitizenActivityIds(UUID.fromString("50586125-bfc9-4e3d-920c-95fa7bc433e5"));
//        for (UUID uuid : array) {
//            System.out.println(uuid.toString());
//        }

//        SOSU sosutest = new SOSU("sosutest", "sosutest");
//        controller.storeSOSU(sosutest, "sosutest");
//        Citizen test2 = new Citizen("test2", "test2", "198112", new Date());
//        controller.controllerDB.storeCitizen(test2, "test2", sosutest);
//        Activity activity = new Activity("test", "test", 1200, 1400, 1, "loltrain.com");
//        controller.controllerDB.storeActivity(activity.getActivityID(), test2.getId(), activity.getName(), activity.getDescription(),
//                activity.getStartTime(), activity.getEndTime(), activity.getDayOfTheWeek(), activity.getPictogramPath());
//
//        System.out.println("Activity info: \n" + Arrays.deepToString(controller.controllerDB.retrieveCitizenActivities(test2.getId())));
//        sosu.addCitizen(james);
//        controller.getDBController().storeCitizen(james, "jamesHotHot", sosu);
//        int auth = controller.getDBController().authenticate("james23", "jamesHotHot");
//        if (auth == 1 || auth == 2) {   //TODO finish
//            System.out.println("Authenticated");
//        } else if (auth == -1) {
//            System.out.println("NOT authenticated :/");
//        }
    }

    
    private Citizen currentCitizen;
    private SOSU currentSosu;
    private final IControllerDB controllerDB;

    public Controller() {
        controllerDB = new DBController();
        currentCitizen = null;
        currentSosu = null;
    }

    public IControllerDB getDBController() {
        return this.controllerDB;
    }

    @Override
    public String getCurrentCitizenName() {
        return getCurrentCitizen().getName();
    }

    @Override
    public String getActivityName(UUID activityID) {
        return currentCitizen.getSchedule().getActivity(activityID).getName();
    }

    @Override
    public String getActivityDescription(UUID activityID) {
        return currentCitizen.getSchedule().getActivity(activityID).getDescription();
    }

    @Override
    public int getActivityStartTime(UUID activityID) {
        return currentCitizen.getSchedule().getActivity(activityID).getStartTime();
    }

    @Override
    public int getActivityEndTime(UUID activityID) {
        return currentCitizen.getSchedule().getActivity(activityID).getEndTime();
    }

    @Override
    public String getPictogramPath(UUID activityID) {
        return currentCitizen.getSchedule().getActivity(activityID).getPictogramPath();
    }

    @Override
    public void storeCitizen(Citizen citizen, String password, SOSU sosu) {
        controllerDB.storeCitizen(citizen, password, sosu);
    }

    @Override
    public int authenticate(String username, String password) {
        return controllerDB.authenticate(username, password);
    }

    @Override
    public void storeSOSU(SOSU sosu, String password) {
        controllerDB.storeSOSU(sosu, password);
    }

    @Override
    public void setCurrentCitizen(UUID id, String username) {
        Schedule schedule = new Schedule();
        String[][] activities = controllerDB.retrieveCitizenActivities(id);
        for (String[] a : activities) {
            schedule.addActivity(new Activity(a[0], a[1], Integer.parseInt(a[2]), Integer.parseInt(a[3]), Integer.parseInt(a[4]), a[5], UUID.fromString(a[6])));
        }
        this.currentCitizen = new Citizen(retrieveCitizenName(id), username, retrieveCitizenCPR(id), retrieveCitizenBirthday(id), id, schedule);
    }

    @Override
    public void setCurrentSosu(UUID id) {
        SOSU sosu = new SOSU(controllerDB.retrieveSosuName(id), controllerDB.retrieveSosuUsername(id), id);
        UUID[] citizenIds = controllerDB.retrieveCitizenIdsForSosu(id);
        for (UUID cID : citizenIds) {
            Schedule schedule = new Schedule();
            String[][] activities = controllerDB.retrieveCitizenActivities(cID);
            for (String[] a : activities) {
                schedule.addActivity(new Activity(a[0], a[1], Integer.parseInt(a[2]), Integer.parseInt(a[3]), Integer.parseInt(a[4]), a[5]));
            }
            sosu.addCitizen(new Citizen(controllerDB.retrieveCitizenName(cID), controllerDB.retrieveSosuUsername(cID), retrieveCitizenCPR(cID), retrieveCitizenBirthday(cID), cID, schedule));
        }
        this.currentSosu = sosu;
    }

    @Override
    public Citizen getCurrentCitizen() {
        return this.currentCitizen;
    }

    @Override
    public SOSU getCurrentSosu() {
        return this.currentSosu;
    }

    @Override
    public String retrieveCitizenCPR(UUID citizenID) {
        return controllerDB.retrieveCitizenCPR(citizenID);
    }

    @Override
    public Date retrieveCitizenBirthday(UUID citizenID) {
        return controllerDB.retrieveCitizenBirthday(citizenID);
    }

    @Override
    public UUID retrieveCitizenID(String username) {
        return controllerDB.retrieveCitizenID(username);
    }

    @Override
    public String retrieveCitizenName(UUID citizenID) {
        return controllerDB.retrieveCitizenName(citizenID);
    }

    @Override
    public String retrieveCitizenUsername(UUID citizenID) {
        return controllerDB.retrieveCitizenUsername(citizenID);
    }

    @Override
    public String retrieveSosuName(UUID citizenID) {
        return controllerDB.retrieveSosuName(citizenID);
    }

    @Override
    public boolean storeActivity(UUID activityID, UUID userID, String name, String description, int start, int top, int day, String pictogramPath) {
        return controllerDB.storeActivity(activityID, userID, name, description, start, top, day, pictogramPath);
    }

    @Override
    public UUID retrieveSosuId(String username) {
        return controllerDB.retrieveSosuId(username);
    }

    @Override
    public UUID[] retrieveCitizenIdsForSosu(UUID sosuID) {
        return controllerDB.retrieveCitizenIdsForSosu(sosuID);
    }

    @Override
    public void deleteActivity(UUID activityId) {
       controllerDB.deleteActivity(activityId);
    }
}
