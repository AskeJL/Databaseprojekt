package database;

import domain.Activity;
import domain.users.Citizen;
import domain.users.SOSU;
import interfaces.IControllerDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.postgresql.util.PSQLException;

public class DBController implements IControllerDB {

    Connection connection;

    @Override
    public String getUserID(String username) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/userdatabase", "postgres", "postgres");) {
            Class.forName("org.postgresql.Driver");
            String sql
                    = "SELECT user_id "
                    + "FROM users "
                    + "WHERE username =?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return rs.getString(1);
        } catch (SQLException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    //Denne metode er tiltænkt en administrator. For systemets normale brug er det vigtigt, at vi primært
    //arbejder i simple typer.
    //Citizens i databases user_password-table har typen "2"
    @Override
    public void storeCitizen(Citizen citizen, String password, SOSU sosu) {
        String id = citizen.getId().toString();
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/userdatabase", "postgres", "postgres");) {
            Class.forName("org.postgresql.Driver");
            String sql
                    = "INSERT INTO citizens "
                    + "VALUES (CAST(? AS uuid), ?, ?, ?,CAST(? AS date), CAST(? AS uuid)); "
                    + "INSERT INTO user_passwords "
                    + "VALUES (CAST(? AS uuid), ?, CAST(2 AS integer));";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, citizen.getId().toString());
            preparedStatement.setString(2, citizen.getName());
            preparedStatement.setString(3, citizen.getUsername());
            preparedStatement.setString(4, citizen.getCPR());
            preparedStatement.setString(5, citizen.getBirthday().toString());
            preparedStatement.setString(6, sosu.getId().toString());
            preparedStatement.setString(7, citizen.getId().toString());
            preparedStatement.setString(8, password);
            int result = preparedStatement.executeUpdate();
            System.out.println(result);
            connection.close();
        } catch (PSQLException e) {
            System.out.println("SQL-error !");
            e.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Citizen retrieveCitizen(String username) {
        return null;
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
            System.out.println("Wrong username/password-combination, or some other PSQL-error !");
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("Class not found!");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("SQL-error");
        }

        return false;
    }

    @Override
    public void storeSOSU(SOSU sosu, String password) {
        
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/userdatabase", "postgres", "postgres");) {
            Class.forName("org.postgresql.Driver");
            String sql
                    = "INSERT INTO sosu "
                    + "VALUES (CAST(? AS uuid), ?, ?); "
                    + "INSERT INTO user_passwords "
                    + "VALUES (CAST(? AS uuid), ?, CAST(1 AS integer));";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, sosu.getId().toString());
            preparedStatement.setString(2, sosu.getName());
            preparedStatement.setString(3, sosu.getUsername());
            preparedStatement.setString(4, sosu.getId().toString());
            preparedStatement.setString(5, password);
            int result = preparedStatement.executeUpdate();
            System.out.println(result);
            connection.close();
        } catch (PSQLException e) {
            System.out.println("SQL-error !");
            e.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
