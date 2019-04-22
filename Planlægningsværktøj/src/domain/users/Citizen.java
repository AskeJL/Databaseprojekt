package domain.users;

import domain.Activity;
import domain.Schedule;
import java.util.Date;

public class Citizen extends User {

    private final String CPR;
    private Date birthday;
    private Schedule schedule;

    public Citizen(String name, String CPR, Date birthday) {
        super(name);
        this.CPR = CPR;
        this.birthday = birthday;
        schedule = new Schedule();
    }

    public Date getBirthday() {
        return birthday;
    }

    public Schedule getSchedule() {
        return schedule;
    }

}
