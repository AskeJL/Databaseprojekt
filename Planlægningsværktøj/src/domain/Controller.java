package domain;

import database.DBController;
import domain.users.Citizen;
import domain.users.SOSU;
import domain.users.User;
import interfaces.IController;
import interfaces.IControllerDB;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Controller implements IController {

    ArrayList<Citizen> tempList;
    Login login;
    Citizen currentUser;
    SOSU currentSosu;
    private final IControllerDB DBController;

    //For database-connection:
    Connection connection;

    public void setCurrentUser(Citizen currentUser) {
        this.currentUser = currentUser;
    }
    
    public void setCurrentSosu(SOSU currentSosu){
        this.currentSosu = currentSosu;
    }
    
    public Citizen getCurrentUser(){
        return this.currentUser;
    }
    
    public SOSU getCurrentSosu(){
        return this.currentSosu;
    }

    public void addActivity(Citizen citizen, String name, String description, int startTime, int endTime, String pictogram, int day) {
        if (tempList.contains(citizen)) {
            citizen.getSchedule().addActivity(new Activity(name, description, startTime, endTime, pictogram, day));
        }
    }

    public Controller() {
        login = new Login(this);
        tempList = new ArrayList<>();
        DBController = new DBController();
    }

    public static void main(String[] args) {
        //Test-kode
        Controller controller = new Controller();
//        SOSU sosu = new SOSU("sosu", "sosu");
//        controller.storeSOSU(sosu, "sosutest");
//        Citizen james = new Citizen("james23", "james23", "1234", new Date());
//        sosu.addCitizen(james);
//        controller.getDBController().storeCitizen(james, "jamesHotHot", sosu);
        int auth = controller.getDBController().authenticate("james23", "jamesHotHot");
        if (auth == 1 || auth == 2) {   //TODO finish
            System.out.println("Authenticated");
        } else if (auth == -1) {
            System.out.println("NOT authenticated :/");
        }
       
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
    public UUID getUserID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUserID(String username) {
        return DBController.getUserID(username);
    }

    @Override
    public void storeCitizen(Citizen citizen, String password, SOSU sosu) {
        DBController.storeCitizen(citizen, password, sosu);
    }

    @Override
    public Citizen retrieveCitizen(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int authenticate(String username, String password) {
        return DBController.authenticate(username, password);
    }

    @Override
    public void storeSOSU(SOSU sosu, String password) {
        DBController.storeSOSU(sosu, password);
    }
}
