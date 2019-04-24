package interfaces;

import domain.users.Citizen;

/**
 *
 * @author Joachim
 */
public interface IControllerDB {
    
    String getUserID(String username, String password);
    
    int storeCitizen(Citizen citizen);
    
    Citizen retrieveCitizen(int serialized_id);
    
    boolean authenticate(String username, String password);
}
