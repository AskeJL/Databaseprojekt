package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Schedule implements Serializable {
    //TODO update sequence diagram for the usecase opretAktivitet
    private ArrayList<Activity>[] schedule;
    
    
    public Schedule(){
        schedule = new ArrayList[7];
        for (int i = 0; i < 7; i++) {
        schedule[i] = new ArrayList<>();    
        }
        
        
    }

    public ArrayList<Activity>[] getSchedule() {
        return schedule;
    }

    
    public void addActivity(Activity activity, int day) {
        schedule[day].add(activity);
        
    }
    
    public void removeActivity(Activity activity){
        for (int i = 0; i < schedule.length; i++) {
            if( schedule[i].contains(activity)){
                schedule[i].remove(activity);
                break;
            }
        }
    }
}
