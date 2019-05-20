package interfaces;

import domain.users.Citizen;
import domain.users.SOSU;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public interface IController {

    ArrayList<UUID> getSchedule(UUID userID);

    String getActivityName(UUID activityID);

    String getActivityDescription(UUID activityID);

    int getActivityStartTime(UUID activityID);

    int getActivityEndTime(UUID activityID);

    String getPictogramPath(UUID activityID);

    void setCurrentCitizen(UUID currentUser, String username);

    void setCurrentSosu(UUID id);

    public Citizen getCurrentCitizen();

    public SOSU getCurrentSosu();

    public String getCurrentCitizenName();

    //Databasereferencer
    // ----------------------------------
    void storeCitizen(Citizen citizen, String password, SOSU sosu);

    void storeSOSU(SOSU sosu, String password);

    int authenticate(String username, String password);

    String retrieveCitizenCPR(UUID citizenID);

    Date retrieveCitizenBirthday(UUID citizenID);

    UUID retrieveCitizenID(String username);

    String retrieveCitizenName(UUID citizenID);

    String retrieveSosuName(UUID citizenID);

    boolean storeActivity(UUID activityID, UUID userID, String name, String description, int start, int top, int day, String pictogramPath);

    UUID retrieveSosuId(String username);

    UUID[] retrieveCitizenIdsForSosu(UUID sosuID);

    public String retrieveCitizenUsername(UUID citizenID);

    public void deleteActivity(UUID activityId);

}
