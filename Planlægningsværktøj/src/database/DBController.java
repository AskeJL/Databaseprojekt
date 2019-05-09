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
import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.postgresql.util.PSQLException;

public class DBController implements IControllerDB {

    Connection connection;
    String url = "jdbc:postgresql://localhost:5432/userdatabase";

    //Denne metode er tiltænkt en administrator. For systemets normale brug er det vigtigt, at vi primært
    //arbejder i simple typer.
    //Citizens i databases user_password-table har typen "2"
    @Override
    public void storeCitizen(Citizen citizen, String password, SOSU sosu) {
        String id = citizen.getId().toString();
        try (Connection connection = DriverManager.getConnection(url, "postgres", "postgres");) {
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
    public void storeSOSU(SOSU sosu, String password) {

        try (Connection connection = DriverManager.getConnection(url, "postgres", "postgres");) {
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
        } catch (SQLException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int authenticate(String username, String password) {
        try (Connection connection = DriverManager.getConnection(url, "postgres", "postgres");) {
            Class.forName("org.postgresql.Driver");
            String sql
                    = "SELECT user_type "
                    + "FROM "
                    + "(select citizen_id AS user_id , username from citizens "
                    + "union "
                    + "select sosu_id AS user_id, username from sosu) AS users "
                    + "natural join user_passwords "
                    + "WHERE users.username =? AND user_passwords.password =?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            connection.close();
        } catch (PSQLException e) {
            System.out.println("Wrong username/password-combination, or some other PSQL-error !");
        } catch (ClassNotFoundException ex) {
            System.out.println("Class not found!");
        } catch (SQLException ex) {
            System.out.println("SQL-error");
        }

        return -1;
    }

    @Override
    public String retrieveCitizenCPR(String username) {
        try (Connection connection = DriverManager.getConnection(url, "postgres", "postgres");) {
            Class.forName("org.postgresql.Driver");
            String sql
                    = "SELECT cpr "
                    + "FROM citizens "
                    + "WHERE username =?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return rs.getString(1);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Date retrieveCitizenBirthday(String username) {
        try (Connection connection = DriverManager.getConnection(url, "postgres", "postgres");) {
            
            Class.forName("org.postgresql.Driver");
            String sql
                    = "SELECT birthday "
                    + "FROM citizens "
                    + "WHERE username =?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return rs.getDate(1);
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public UUID retrieveCitizenID(String username) {
        try (Connection connection = DriverManager.getConnection(url, "postgres", "postgres");) {
            Class.forName("org.postgresql.Driver");
            String sql
                    = "SELECT citizen_id "
                    + "FROM citizens "
                    + "WHERE username =?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return UUID.fromString(rs.getString(1));

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String retrieveCitizenName(String username) {
        try (Connection connection = DriverManager.getConnection(url, "postgres", "postgres");) {
            Class.forName("org.postgresql.Driver");
            String sql
                    = "SELECT name "
                    + "FROM citizens "
                    + "WHERE username =?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return rs.getString(1);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String retrieveSOSUName(String username) {
        try (Connection connection = DriverManager.getConnection(url, "postgres", "postgres");) {
            Class.forName("org.postgresql.Driver");
            String sql
                    = "SELECT name "
                    + "FROM sosu "
                    + "WHERE username =?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return rs.getString(1);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
