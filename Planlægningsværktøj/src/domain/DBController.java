
package domain;

import domain.users.Citizen;
import interfaces.IControllerDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.postgresql.util.PSQLException;

/**
 * @author Joachim
 */
public class DBController implements IControllerDB {
    Connection connection;

    @Override
    public String getUserID(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //Denne metode er tiltænkt en administrator. For systemets normale brugere er det vigtigt, at vi primært
    //arbejder i simple typer.
    @Override
    public void storeCitizen(Citizen citizen, String password) {
        String id = citizen.getId().toString();
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/userdatabase", "postgres", "postgres");) {
            Class.forName("org.postgresql.Driver");
            String sql
                    = "INSERT INTO users " +
                      "VALUES (CAST(? AS uuid), CAST(? AS int),?,?); " +
                      "INSERT INTO user_passwords "+
                      "VALUES (CAST(? AS uuid), ?);";
        
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, citizen.getId().toString());
            preparedStatement.setString(2, Integer.toString(2));
            preparedStatement.setString(3, citizen.getName());
            preparedStatement.setString(4, citizen.getUsername());
            preparedStatement.setString(5, citizen.getId().toString());
            preparedStatement.setString(6, password);
            int result = preparedStatement.executeUpdate();
            System.out.println(result);
            connection.close();
        } catch (PSQLException e) {
            System.out.println("SQL-error !");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public Citizen retrieveCitizen(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean authenticate(String username, String password) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/userdatabase", "postgres", "postgres");) {
            Class.forName("org.postgresql.Driver");
            String sql
                    = "SELECT * "
                    + "FROM users, user_passwords "
                    + "WHERE users.user_id = user_passwords.user_id "
                    + "AND users.username =? AND user_passwords.password =?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return true;
            }
            connection.close();
        } catch (PSQLException e) {
            System.out.println("Wrong username/password-combination, or some other SQL-error !");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
