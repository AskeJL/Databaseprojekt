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
        SOSU sosutest = new SOSU("sosutest", "sosutest");
        controller.storeSOSU(sosutest, "sosutest");

        Citizen test2 = new Citizen("test2", "test2", "198112", new Date());

        controller.DBController.storeCitizen(test2, "test2", sosutest);

        Activity activity = new Activity("test", "test", 1200, 1400, 1, "loltrain.com");
        controller.DBController.storeActivity(activity.getActivityID(), test2.getId(), activity.getName(), activity.getDescription(),
                 activity.getStartTime(), activity.getEndTime(), activity.getDayOfTheWeek(), activity.getPictogramPath());

        System.out.println("Activity info: \n" + Arrays.deepToString(controller.DBController.retrieveCitizenActivities(test2.getId())));

//        sosu.addCitizen(james);
//        controller.getDBController().storeCitizen(james, "jamesHotHot", sosu);
//        int auth = controller.getDBController().authenticate("james23", "jamesHotHot");
//        if (auth == 1 || auth == 2) {   //TODO finish
//            System.out.println("Authenticated");
//        } else if (auth == -1) {
//            System.out.println("NOT authenticated :/");
//        }
    }

    ArrayList<Citizen> tempList;
    Login login;
    Citizen currentCitizen;
    SOSU currentSosu;
    private final IControllerDB DBController;
    //For database-connection:
    Connection connection;

    public void addActivity(Citizen citizen, String name, String description, int startTime, int endTime, String pictogram, int day) {
        if (tempList.contains(citizen)) {
            citizen.getSchedule().addActivity(new Activity(name, description, startTime, endTime, day, pictogram));
        }
    }

    public Controller() {
        login = new Login(this);
        tempList = new ArrayList<>();
        DBController = new DBController();
        currentCitizen = null;
        currentSosu = null;
    }

    public IControllerDB getDBController() {
        return this.DBController;
    }

    private Activity getActivity(UUID userID, UUID activityID) {
        //TODO update 
        return null;
    }

    @Override
    public ArrayList<UUID> getSchedule(UUID userID) {
        ArrayList<UUID> returnSchedule = new ArrayList<>();
        //TODO Update
        //ArrayList<Activity> originalSchedule = getCitizen(userID).getSchedule().getSchedule();
        //for (Activity activity : originalSchedule) {
        //   returnSchedule.add(activity.getActivityID());
        //}
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

    @Override
    public void storeCitizen(Citizen citizen, String password, SOSU sosu) {
        DBController.storeCitizen(citizen, password, sosu);
    }

    @Override
    public int authenticate(String username, String password) {
        return DBController.authenticate(username, password);
    }

    @Override
    public void storeSOSU(SOSU sosu, String password) {
        DBController.storeSOSU(sosu, password);
    }

    @Override
    public void setCurrentCitizen(Citizen currentUser) {
        this.currentCitizen = currentUser;
    }

    @Override
    public void setCurrentSosu(SOSU currentSosu) {
        this.currentSosu = currentSosu;
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
    public String retrieveCitizenCPR(String username) {
        return DBController.retrieveCitizenCPR(username);
    }

    @Override
    public Date retrieveCitizenBirthday(String username) {
        return DBController.retrieveCitizenBirthday(username);
    }

    @Override
    public UUID retrieveCitizenID(String username) {
        return DBController.retrieveCitizenID(username);
    }

    @Override
    public String retrieveCitizenName(String username) {
        return DBController.retrieveCitizenName(username);
    }

    @Override
    public String retrieveSOSUName(String username) {
        return DBController.retrieveSOSUName(username);
    }

    @Override
    public void storeActivity(UUID activityID, UUID userID, String name, String description, int start, int top, int day, String pictogramPath) {
        DBController.storeActivity(activityID, userID, name, description, start, top, day, pictogramPath);
    }
}
