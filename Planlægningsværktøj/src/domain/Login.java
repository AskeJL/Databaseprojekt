package domain;

import domain.users.Citizen;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login {

    final String fileName = "Logins.txt";

    public Login() {

    }

    public boolean Authenticate(String username, String password) {
        Citizen citizen = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            while (true) {
                citizen = (Citizen) inputStream.readObject();
                if (username.equalsIgnoreCase(citizen.getName()) && citizen.AuthenticateCPR(password)) {
                    return true;
                }
            }
        } catch (EOFException eof) {
            System.out.println("Reading Done!" + eof);
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

}
