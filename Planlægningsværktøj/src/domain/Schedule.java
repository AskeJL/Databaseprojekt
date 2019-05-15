package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

public class Schedule implements Serializable {

    //TODO update sequence diagram for the usecase opretAktivitet
    private ArrayList<Activity> schedule;

    public Schedule() {
        schedule = new ArrayList();
    }

    public Schedule(ArrayList<Activity> schedule) {
        this.schedule = schedule;
    }

    public ArrayList<Activity> getSchedule() {
        return schedule;
    }

    public void addActivity(Activity activity) {
        schedule.add(activity);
        Collections.sort(schedule);
    }

    public Activity getActivity(UUID activityID) {
        for (Activity activity : schedule) {
            if (activity.getActivityID().equals(activityID)) {
                return activity;
            }
        }
        return null;
    }

    public UUID getActivityId(String name) {
        for (Activity activity : schedule) {
            if (activity.getName().equals(name)) {
                return activity.getActivityID();
            }
        }
        return null;
    }
    
    public ArrayList<String> getActivityNamesOfday(int day){
        ArrayList<String> al = new ArrayList<>();
        for (Activity a : schedule){
            if (a.getDayOfTheWeek() == day){
                al.add(a.getName());
            }
        }
        return al;
    }

    public String toString() {      //TODO maybe delete or update
        String returnString = "";
        for (Activity activity : schedule) {
            returnString += activity.getName();
            returnString += " ";
        }
        return returnString;
    }

    public void removeActivity(Activity activity) {
        if (schedule.contains(activity)) {
            schedule.remove(activity);
        }
    }

    public void addActivity(String name, String description, int sTime, int eTime, int day, String path) {
        Activity activity = new Activity(name, description, sTime, eTime, day, path);
        this.addActivity(activity);
    }
}
