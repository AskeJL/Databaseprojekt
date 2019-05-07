package interfaces;

import domain.users.Citizen;
import domain.users.SOSU;
import java.sql.Connection;

/**
 *
 * @author Joachim
 */
public interface IControllerDB {
    
    String getUserID(String username);
    
    void storeCitizen(Citizen citizen, String password, SOSU sosu);
    
    void storeSOSU(SOSU sosu, String password);
    
    Citizen retrieveCitizen(String username);
    
    //Evt String / type ud, så den selv finder login-type
    boolean authenticate(String username, String password);
}
