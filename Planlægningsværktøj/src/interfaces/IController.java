package interfaces;

import domain.users.Citizen;
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
    
    void storeCitizen(Citizen citizen, String password);
    
    Citizen retrieveCitizen(String username, String password);
    
    boolean authenticate(String username, String password);

}
