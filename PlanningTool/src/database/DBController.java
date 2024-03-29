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

    //Følgende to metoder er tiltænkt en administrator. For systemets normale brug er det vigtigt, at vi primært
    //arbejder i simple typer.
    //Citizens i databasens user_password-table har typen "2", SOSU har typen "1"
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
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, citizen.getName());
            preparedStatement.setString(3, citizen.getUsername());
            preparedStatement.setString(4, citizen.getCPR());
            preparedStatement.setString(5, citizen.getBirthday().toString());
            preparedStatement.setString(6, sosu.getId().toString());
            preparedStatement.setString(7, citizen.getId().toString());
            preparedStatement.setString(8, password);
            int result = preparedStatement.executeUpdate();
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
                return rs.getInt("user_type");
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
                    = "SELECT CPR "
                    + "FROM citizens "
                    + "WHERE citizen_id = CAST(? AS uuid);";
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
    public UUID retrieveCitizenID(String username) {
        try (Connection connection = DriverManager.getConnection(url, "postgres", "postgres");) {
            Class.forName("org.postgresql.Driver");
            String sql
                    = "SELECT citizen_id "
                    + "FROM citizens "
                    + "WHERE username = ?";
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
    public String retrieveCitizenUsername(UUID citizenID) {
        try (Connection connection = DriverManager.getConnection(url, "postgres", "postgres");) {
            Class.forName("org.postgresql.Driver");
            String sql
                    = "SELECT username "
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
    public String retrieveSosuName(UUID sosuID) {
        try (Connection connection = DriverManager.getConnection(url, "postgres", "postgres");) {
            Class.forName("org.postgresql.Driver");
            String sql
                    = "SELECT name "
                    + "FROM sosu "
                    + "WHERE sosu_id = CAST(? AS uuid)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, sosuID.toString());
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
        int activityParameterCount = 7;
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
                    = "SELECT name, description, start_time, stop_time, day, pictogram_path, activity_id "
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
    public boolean storeActivity(UUID ActivityID, UUID userID, String name, String description, int start, int stop, int day, String pictogramPath) {
        int result = 0;
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
            result = preparedStatement.executeUpdate();
            if (result > 0) {
                return true;
            }
            connection.close();
        } catch (PSQLException e) {
            System.out.println("SQL-error !");
            e.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public UUID retrieveSosuId(String username) {
        try (Connection connection = DriverManager.getConnection(url, "postgres", "postgres");) {
            Class.forName("org.postgresql.Driver");
            String sql
                    = "SELECT sosu_id "
                    + "FROM sosu "
                    + "WHERE username = ?";
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
    public UUID[] retrieveCitizenIdsForSosu(UUID sosuID) {
        UUID[] array;
        try (Connection connection = DriverManager.getConnection(url, "postgres", "postgres");) {
            Class.forName("org.postgresql.Driver");
            String sql1
                    = "SELECT count(Citizen_id) "
                    + "FROM citizens "
                    + "WHERE citizens.sosu_id IN "
                    + "(SELECT sosu_id "
                    + "FROM sosu "
                    + "WHERE sosu_id = CAST(? AS UUID));";

            PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
            preparedStatement1.setString(1, sosuID.toString());
            ResultSet rs1 = preparedStatement1.executeQuery();
            rs1.next();
            int arraySize = rs1.getInt(1);
            array = new UUID[arraySize];

            String sql2
                    = "SELECT Citizen_id "
                    + "FROM citizens "
                    + "WHERE citizens.sosu_id IN "
                    + "(SELECT sosu_id "
                    + "FROM sosu "
                    + "WHERE sosu_id = CAST(? AS UUID));";
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
            preparedStatement2.setString(1, sosuID.toString());
            ResultSet rs2 = preparedStatement2.executeQuery();
            int counter = 0;
            while (rs2.next()) {
                array[counter] = UUID.fromString(rs2.getString(1));
                counter++;
            }
            return array;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String retrieveSosuUsername(UUID sosuID) {
        try (Connection connection = DriverManager.getConnection(url, "postgres", "postgres");) {
            Class.forName("org.postgresql.Driver");
            String sql
                    = "SELECT username "
                    + "FROM sosu "
                    + "WHERE sosu_id = CAST(? AS uuid)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, sosuID.toString());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void deleteActivity(UUID activityId) {
        try (Connection connection = DriverManager.getConnection(url, "postgres", "postgres");) {
            Class.forName("org.postgresql.Driver");
            String sql
                    = "DELETE "
                    + "FROM activities "
                    + "WHERE activity_id = CAST(? AS UUID)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, activityId.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    @Override
//    public UUID[] retrieveCitizenActivityIds(UUID citizenId) {
//        UUID[] array;
//        try (Connection connection = DriverManager.getConnection(url, "postgres", "postgres");) {
//            Class.forName("org.postgresql.Driver");
//            String sql1
//                    = "SELECT count(activity_id) "
//                    + "FROM activities "
//                    + "WHERE activities.citizen_id IN "
//                    + "(SELECT citizen_id "
//                    + "FROM citizens "
//                    + "WHERE citizen_id = CAST(? AS UUID));";
//
//            PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
//            preparedStatement1.setString(1, citizenId.toString());
//            ResultSet rs1 = preparedStatement1.executeQuery();
//            rs1.next();
//            int arraySize = rs1.getInt(1);
//            array = new UUID[arraySize];
//
//            String sql2
//                    = "SELECT activity_id "
//                    + "FROM activities "
//                    + "WHERE activities.citizen_id IN "
//                    + "(SELECT citizen_id "
//                    + "FROM citizens "
//                    + "WHERE citizen_id = CAST(? AS UUID));";
//            PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
//            preparedStatement2.setString(1, citizenId.toString());
//            ResultSet rs2 = preparedStatement2.executeQuery();
//            int counter = 0;
//            while (rs2.next()) {
//                array[counter] = UUID.fromString(rs2.getString(1));
//                counter++;
//            }
//            return array;
//
//        } catch (SQLException ex) {
//            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
//    @Override
//    public UUID[] retrieveCitizenActivityIdsForGivenDay(UUID citizenId, int day) {
//        UUID[] array;
//        try (Connection connection = DriverManager.getConnection(url, "postgres", "postgres");) {
//            Class.forName("org.postgresql.Driver");
//            String sql1
//                    = "SELECT count(activity_id) "
//                    + "FROM activities "
//                    + "WHERE activities.citizen_id IN "
//                    + "(SELECT citizen_id "
//                    + "FROM citizens "
//                    + "WHERE citizen_id = CAST(? AS UUID)) "
//                    + "AND activities.day = CAST(? AS integer);";
//
//            PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
//            preparedStatement1.setString(1, citizenId.toString());
//            preparedStatement1.setString(2, String.valueOf(day));
//            ResultSet rs1 = preparedStatement1.executeQuery();
//            rs1.next();
//            int arraySize = rs1.getInt(1);
//            array = new UUID[arraySize];
//
//            String sql2
//                    = "SELECT activity_id "
//                    + "FROM activities "
//                    + "WHERE activities.citizen_id IN "
//                    + "(SELECT citizen_id "
//                    + "FROM citizens "
//                    + "WHERE citizen_id = CAST(? AS UUID)) "
//                    + "AND activities.day = CAST(? AS integer);";
//            PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
//            preparedStatement2.setString(1, citizenId.toString());
//            preparedStatement2.setString(2, String.valueOf(day));
//            ResultSet rs2 = preparedStatement2.executeQuery();
//            int counter = 0;
//            while (rs2.next()) {
//                array[counter] = UUID.fromString(rs2.getString(1));
//                counter++;
//            }
//            for (UUID id : array){
//                System.out.println(id.toString());
//            }
//            return array;
//
//        } catch (SQLException ex) {
//            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
}
