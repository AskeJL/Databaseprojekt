package interfaces;

import domain.users.Citizen;
import domain.users.SOSU;
import java.sql.Connection;
import java.util.ArrayList;
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

    String retrieveCitizenCPR(String username);

    Date retrieveCitizenBirthday(String username);

    UUID retrieveCitizenID(String username);
    
    String retrieveCitizenName(String username);
    
    String retrieveSOSUName(String username);
    
    ArrayList<String[]> retrieveCitizenActivies(UUID userID);
    
    
}
