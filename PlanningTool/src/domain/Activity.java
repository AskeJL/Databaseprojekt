package domain;

import java.util.UUID;

public class Activity implements Comparable<Activity> {

    private String name;
    private String description;
    private int startTime;
    private int endTime;
    private String pictogramPath;
    private final UUID activityID;
    private int dayOfTheWeek;

    public Activity(String name, String description, int startTime, int endTime, int day, String pictogramPath) {
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityID = UUID.randomUUID();
        this.dayOfTheWeek = day;
        this.pictogramPath = pictogramPath;
    }

    public Activity(String name, String description, int startTime, int endTime, int day, String pictogramPath, UUID activityID) {
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayOfTheWeek = day;
        this.pictogramPath = pictogramPath;
        this.activityID = activityID;
    }

    public UUID getActivityID() {
        return activityID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public String getPictogramPath() {
        return pictogramPath;
    }

    public void setPictogramPath(String pictogramPath) {
        this.pictogramPath = pictogramPath;
    }

    public int getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(int dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    @Override
    public int compareTo(Activity o) {
        int dif = this.dayOfTheWeek - o.dayOfTheWeek;
        if (dif == 0) {
            dif = this.startTime - o.startTime;
            if (dif == 0) {
                dif = this.endTime - o.endTime;
                if (dif == 0) {
                    dif = this.name.compareTo(o.name);
                }
            }
        }
        return dif;
    }

}
