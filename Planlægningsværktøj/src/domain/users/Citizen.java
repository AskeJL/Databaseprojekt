package domain.users;

import domain.Activity;
import domain.Schedule;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Citizen extends User implements Serializable {

    private final String CPR;
    private Date birthday;
    private Schedule schedule;

    public Citizen(String name, String username, String CPR, Date birthday) {
        super(name, username);
        this.CPR = CPR;
        this.birthday = birthday;
        schedule = new Schedule();
    }

    public Citizen(String name, String username, String CPR, Date birthday, UUID uuid) {
        super(name, username, uuid);
        this.CPR = CPR;
        this.birthday = birthday;
        schedule = new Schedule();
    }

//    public Citizen(String name, String username, String CPR, Date birthday, Schedule schedule) {
//        super(name, username);
//        this.CPR = CPR;
//        this.birthday = birthday;
//        this.schedule = schedule;
//    }

    public Date getBirthday() {
        return birthday;
    }

    public String getCPR() {
        return this.CPR;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public boolean AuthenticateCPR(String CPR) {
        return this.CPR.equals(CPR);

    }

    @Override
    public String toString() {
        return this.getName();
    }

}
