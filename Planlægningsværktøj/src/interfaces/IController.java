package interfaces;

import domain.users.Citizen;
import domain.users.SOSU;
import java.util.ArrayList;
import java.util.UUID;

public interface IController {

    UUID getUserID();

    ArrayList<UUID> getSchedule(UUID userID);

    String getActivityName(UUID userID, UUID activityID);

    String getActivityDescription(UUID userID, UUID activityID);

    int getActivityStartTime(UUID userID, UUID activityID);

    int getActivityEndTime(UUID userID, UUID activityID);

    String getPictogramPath(UUID userID, UUID activityID);
    
    String getUserID(String username);
    
    void storeCitizen(Citizen citizen, String password, SOSU sosu);
    
    void storeSOSU(SOSU sosu, String password);
    
    Citizen retrieveCitizen(String username, String password);
    
    int authenticate(String username, String password);

}
