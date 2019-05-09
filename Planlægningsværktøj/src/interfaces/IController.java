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

    public void setCurrentCitizen(Citizen currentUser);

    public void setCurrentSosu(SOSU currentSosu);

    public Citizen getCurrentCitizen();

    public SOSU getCurrentSosu();

    //Databasereferencer
    // ----------------------------------
    void storeCitizen(Citizen citizen, String password, SOSU sosu);

    void storeSOSU(SOSU sosu, String password);

    int authenticate(String username, String password);
    
    String retrieveCitizenCPR(String username);

    Date retrieveCitizenBirthday(String username);

    UUID retrieveCitizenID(String username);
    
    String retrieveCitizenName(String username);
    
    String retrieveSOSUName(String username);
}
