package interfaces;

import domain.users.Citizen;
import java.sql.Connection;

/**
 *
 * @author Joachim
 */
public interface IControllerDB {
    
    String getUserID(String username, String password);
    
    void storeCitizen(Citizen citizen, String password);
    
    Citizen retrieveCitizen(String username, String password);
    
    boolean authenticate(String username, String password);
    
    public String getCitizen(String username, String name);
}
