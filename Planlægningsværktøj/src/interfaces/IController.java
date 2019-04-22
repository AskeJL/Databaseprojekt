/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author Joachim
 */
public interface IController {

    ArrayList<UUID> getSchedule(UUID userID);

    String getActivityName(UUID userID, UUID activityID);

    String getActivityDescription(UUID userID, UUID activityID);
    
    int getActivityStartTime(UUID userID, UUID activityID);
    
    int getActivityEndTime(UUID userID, UUID activityID);
    
    String getPictogramPath(UUID userID, UUID activityID);

}
