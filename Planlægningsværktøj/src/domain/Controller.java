package domain;

import database.DBController;
import domain.users.Citizen;
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
    User currentUser;
    private final IControllerDB DBController;
    
    //For database-connection:
    Connection connection;

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
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
        
        Citizen james = new Citizen("james23", "james23", "1234", new Date());
        controller.getDBController().storeCitizen(james, "jamesHotHot");
        if (controller.getDBController().authenticate("james23", "jamesHotHot")){
            System.out.println("Authenticated");
        }
        else{
            System.out.println("NOT authenticated :/");
        }
        System.out.println(controller.getUserID("james23"));
        
    }
    
    public IControllerDB getDBController(){
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
    public void storeCitizen(Citizen citizen, String password) {
        DBController.storeCitizen(citizen, password);
    }

    @Override
    public Citizen retrieveCitizen(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean authenticate(String username, String password) {
        return DBController.authenticate(username, password);
    }
   }

