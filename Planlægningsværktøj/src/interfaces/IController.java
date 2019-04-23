package interfaces;

import java.util.ArrayList;
import java.util.UUID;

public interface IController {

    ArrayList<UUID> getSchedule(UUID userID);

    String getActivityName(UUID userID, UUID activityID);

    String getActivityDescription(UUID userID, UUID activityID);

    int getActivityStartTime(UUID userID, UUID activityID);

    int getActivityEndTime(UUID userID, UUID activityID);

    String getPictogramPath(UUID userID, UUID activityID);

    boolean authenticate(String name, String CPR);
}
