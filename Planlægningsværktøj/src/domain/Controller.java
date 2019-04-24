package domain;

import domain.users.Citizen;
import domain.users.User;
import java.lang.Object;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import interfaces.IController;
import interfaces.IControllerDB;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.UUID;
import jdk.nashorn.internal.codegen.CompilerConstants;

public class Controller implements IController, IControllerDB {

    ArrayList<Citizen> tempList;
    Login login;
    User currentUser;

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void addActivity(Citizen citizen, String name, String description, int startTime, int endTime, String pictogram, int day) {
        if (tempList.contains(citizen)) {
            citizen.getSchedule().addActivity(new Activity(name, description, startTime, endTime, pictogram, day));
        }
    }

    public Controller() {
        login = new Login(this);
        tempList = new ArrayList<>();
    }

    public static void main(String[] args) {
        //Test-kode
        Controller controller = new Controller();
        Citizen lars = new Citizen("GEE GEE", "1234", new Date());
        
        int larsInt = controller.storeCitizen(lars);
        System.out.println("lars has been stored, and his serializableID is now "+larsInt);
        System.out.println("Now to retrieve him.");
        lars = controller.retrieveCitizen(larsInt);
        System.out.println("And here we have him:" + lars.getName());

//        final String SQL_SERIALIZE_OBJECT = "INSERT INTO serialized_java_objects(object_name, serialized_object) VALUES (?, ?)";
//        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb", "root", "");) {
//            Class.forName("com.mysql.jdbc.Driver");
//            PreparedStatement ps = connection.prepareStatement(SQL_SERIALIZE_OBJECT, Statement.RETURN_GENERATED_KEYS);
//            ps.setString(1, lars.getClass().getName());
//            ps.setObject(2, lars);
//            ps.executeUpdate();
//            ResultSet rs = ps.getGeneratedKeys();
//            int serializable_id = -1;
//            if (rs.next()) {
//                serializable_id = rs.getInt(1);
//            }
//            rs.close();
//            ps.close();
//            System.out.println(serializable_id);
//        } catch (Exception e) {
//            System.out.println("An error occured in the handling of database-request.");
//            e.printStackTrace();
//        }
        

//        Citizen anders = new Citizen("Anders And", "5678", new Date());
//        Citizen katrine = new Citizen("Katrine", "3456", new Date());
//        try (ObjectOutputStream outputStream = new ObjectOutputStream(
//                new FileOutputStream("Logins.txt", true))) {
//
//            outputStream.writeObject(lars);
//            outputStream.writeObject(anders);
//            outputStream.writeObject(katrine);
//
//        } catch (IOException ex) {
//            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        controller.tempList.add(lars);
//        controller.addActivity(lars, "onani", "at onanere", 800, 1200, null, 6);
//        controller.addActivity(lars, "onanifirst", "at onanere", 800, 1200, null, 5);
//        controller.addActivity(lars, "onanisecond", "at onanere", 700, 1200, null, 6);
//        System.out.println(controller.tempList.get(0).getSchedule().toString());
        //System.out.println(login.authenticate("Anders And", "5678"));
    }

    private Activity getActivity(UUID userID, UUID activityID) {
        //TODO update 
        return null;
    }

    @Override
    public ArrayList<UUID> getSchedule(UUID userID) {
        ArrayList<UUID> returnSchedule = new ArrayList<>();
        //TODO Update
        //ArrayList<Activity> originalSchedule = getCitizen(userID).getSchedule().getSchedule();
        //for (Activity activity : originalSchedule) {
        //   returnSchedule.add(activity.getActivityID());
        //}
        return returnSchedule;
    }

    @Override
    public String getActivityName(UUID userID, UUID activityID) {
        return getActivity(userID, activityID).getName();
    }

    @Override
    public String getActivityDescription(UUID userID, UUID activityID) {
        return getActivity(userID, activityID).getDescription();
    }

    @Override
    public int getActivityStartTime(UUID userID, UUID activityID) {
        return getActivity(userID, activityID).getStartTime();
    }

    @Override
    public int getActivityEndTime(UUID userID, UUID activityID) {
        return getActivity(userID, activityID).getEndTime();
    }

    @Override
    public String getPictogramPath(UUID userID, UUID activityID) {
        return getActivity(userID, activityID).getPictogramPath();
    }

    @Override
    public boolean authenticate(String username, String password) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb", "root", "");) {
            Class.forName("com.mysql.jdbc.Driver");
            String sql = "Select * from userdatabase where username=? and password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return true;
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public UUID getUserID() {
        return currentUser.getId();
    }

    @Override
    public String getUserID(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int storeCitizen(Citizen citizen) {
        final String SQL_SERIALIZE_OBJECT = "INSERT INTO serialized_java_objects(object_name, serialized_object) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb", "root", "");) {
            Class.forName("com.mysql.jdbc.Driver");
            PreparedStatement ps = connection.prepareStatement(SQL_SERIALIZE_OBJECT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, citizen.getClass().getName());
            ps.setObject(2, citizen);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            int serializable_id = -1;
            if (rs.next()) {
                serializable_id = rs.getInt(1);
            }
            rs.close();
            ps.close();
            return serializable_id;
        } catch (Exception e) {
            System.out.println("An error occured in the handling of database-request.");
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Citizen retrieveCitizen(int serialized_id) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb", "root", "");) {
            Class.forName("com.mysql.jdbc.Driver");
            PreparedStatement ps = connection.prepareStatement("SELECT serialized_object FROM serialized_java_objects WHERE serialized_id = ?");
            ps.setLong(1, serialized_id);
            ResultSet rs = ps.executeQuery();
            rs.next();

            byte[] buf = rs.getBytes(1);
            ObjectInputStream objectIn = null;
            if (buf != null) {
                objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));
            }
            Citizen deSerializedCitizen = (Citizen)objectIn.readObject();
            return deSerializedCitizen;

        } catch (Exception e) {
            System.out.println("Error in retrieving citizen from Database.");
            e.printStackTrace();
        }
        return null;
    }
}
