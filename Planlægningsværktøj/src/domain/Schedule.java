package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

public class Schedule {

    //TODO update sequence diagram for the usecase opretAktivitet
    private ArrayList<Activity> schedule;

    public Schedule() {
        schedule = new ArrayList();
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

    public String toString() {
        String returnString = "";
        for (Activity activity : schedule) {
            returnString += activity.getName();
            returnString += " ";
        }
        return returnString;
    }
}
