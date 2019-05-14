package interfaces;

import domain.users.Citizen;
import domain.users.SOSU;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author Joachim
 */
public interface IControllerDB {

    //Admin-metoder:
    void storeCitizen(Citizen citizen, String password, SOSU sosu);

    void storeSOSU(SOSU sosu, String password);

    //System-metoder:
    int authenticate(String username, String password);

    String retrieveCitizenCPR(UUID citizenID);

    Date retrieveCitizenBirthday(UUID citizenID);

    UUID retrieveCitizenID(String username);

    String retrieveCitizenName(UUID citizenID);

    String[][] retrieveCitizenActivities(UUID citizenID);
    
     void storeActivity(UUID activityID, UUID userID, String name, String description, int start, int top, int day, String pictogramPath);
    
    //(delete) methods for retrieving a sosu-object, to be set as currentSOSU
    UUID retrieveSosuId (String username);
    
    String retrieveSosuName(UUID sosuID);
    
    String retrieveSosuUsername(UUID sosuID);
    
    UUID[] retrieveCitizenIdsForSosu(UUID sosuID);

   

}
