package database;

import domain.users.Citizen;
import domain.users.SOSU;
import interfaces.IControllerDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public String retrieveCitizenCPR(UUID id) {
        try (Connection connection = DriverManager.getConnection(url, "postgres", "postgres");) {
            Class.forName("org.postgresql.Driver");
            String sql
                    = "SELECT cpr "
                    + "FROM citizens "
                    + "WHERE citizen_id = CAST(? AS uuid)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id.toString());
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return rs.getString(1);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Date retrieveCitizenBirthday(UUID id) {
        try (Connection connection = DriverManager.getConnection(url, "postgres", "postgres");) {

            Class.forName("org.postgresql.Driver");
            String sql
                    = "SELECT birthday "
                    + "FROM citizens "
                    + "WHERE citizen_id = CAST(? AS uuid)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id.toString());
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return rs.getDate(1);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public UUID retrieveCitizenID(UUID id) {
        try (Connection connection = DriverManager.getConnection(url, "postgres", "postgres");) {
            Class.forName("org.postgresql.Driver");
            String sql
                    = "SELECT citizen_id "
                    + "FROM citizens "
                    + "WHERE citizen_id = CAST(? AS uuid)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id.toString());
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return UUID.fromString(rs.getString(1));

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String retrieveCitizenName(UUID citizenID) {
        try (Connection connection = DriverManager.getConnection(url, "postgres", "postgres");) {
            Class.forName("org.postgresql.Driver");
            String sql
                    = "SELECT name "
                    + "FROM citizens "
                    + "WHERE citizen_id = CAST(? AS uuid)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, citizenID.toString());
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return rs.getString(1);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String retrieveSOSUName(UUID citizenID) {
        try (Connection connection = DriverManager.getConnection(url, "postgres", "postgres");) {
            Class.forName("org.postgresql.Driver");
            String sql
                    = "SELECT name "
                    + "FROM sosu "
                    + "WHERE citizen_id = CAST(? AS uuid)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, citizenID.toString());
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return rs.getString(1);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String[][] retrieveCitizenActivities(UUID userID) {
        String[][] toBeReturned = null;
        int arraySize;
        int activityParameterCount = 6;
        try (Connection connection = DriverManager.getConnection(url, "postgres", "postgres");) {
            Class.forName("org.postgresql.Driver");
            String sql1
                    = "SELECT COUNT(name) "
                    + "FROM activities "
                    + "WHERE citizen_id = CAST(? AS uuid)";
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
            preparedStatement1.setString(1, userID.toString());
            ResultSet rs1 = preparedStatement1.executeQuery();
            rs1.next();
            arraySize = rs1.getInt(1);
            toBeReturned = new String[arraySize][activityParameterCount];
            String sql2
                    = "SELECT name, description, start_time, stop_time, day, pictogram_path "
                    + "FROM activities "
                    + "WHERE citizen_id = CAST(? AS uuid)";
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
            preparedStatement2.setString(1, userID.toString());
            ResultSet rs2 = preparedStatement2.executeQuery();

            int counter = 0;
            while (rs2.next()) {
                for (int j = 0; j < activityParameterCount; j++) {
                    //SQL-kolonne-referencer begynder ved 1, og ikke 0.
                    toBeReturned[counter][j] = (String) rs2.getString(j + 1);
                }
                counter++;
            }

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return toBeReturned;
    }

    @Override
    public void storeActivity(UUID ActivityID, UUID userID, String name, String description, int start, int stop, int day, String pictogramPath) {
        try (Connection connection = DriverManager.getConnection(url, "postgres", "postgres");) {
            Class.forName("org.postgresql.Driver");
            String sql
                    = "INSERT INTO activities "
                    + "VALUES (CAST(? AS uuid),CAST(? AS uuid), ?, ?, CAST(? AS integer),"
                    + "CAST(? AS integer),CAST(? AS integer),?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ActivityID.toString());
            preparedStatement.setString(2, userID.toString());
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, description);
            preparedStatement.setString(5, String.valueOf(start));
            preparedStatement.setString(6, String.valueOf(stop));
            preparedStatement.setString(7, String.valueOf(day));
            preparedStatement.setString(8, pictogramPath);
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
