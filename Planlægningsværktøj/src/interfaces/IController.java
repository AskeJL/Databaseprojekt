package interfaces;

import domain.users.Citizen;
import domain.users.SOSU;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public interface IController {

    ArrayList<UUID> getSchedule(UUID userID);

    String getActivityName(UUID userID, UUID activityID);

    String getActivityDescription(UUID userID, UUID activityID);

    int getActivityStartTime(UUID userID, UUID activityID);

    int getActivityEndTime(UUID userID, UUID activityID);

    String getPictogramPath(UUID userID, UUID activityID);

    public void setCurrentCitizen(UUID currentUser, String username);

    public void setCurrentSosu(SOSU currentSosu);

    public Citizen getCurrentCitizen();

    public SOSU getCurrentSosu();

    //Databasereferencer
    // ----------------------------------
    void storeCitizen(Citizen citizen, String password, SOSU sosu);

    void storeSOSU(SOSU sosu, String password);

    int authenticate(String username, String password);

    String retrieveCitizenCPR(UUID citizenID);

    Date retrieveCitizenBirthday(UUID citizenID);

    UUID retrieveCitizenID(String username);

    String retrieveCitizenName(UUID citizenID);

    String retrieveSOSUName(UUID citizenID);

    void storeActivity(UUID activityID, UUID userID, String name, String description, int start, int top, int day, String pictogramPath);

}
