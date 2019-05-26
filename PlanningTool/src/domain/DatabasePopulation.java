package domain;

import domain.users.Citizen;
import domain.users.SOSU;
import interfaces.IController;

public class DatabasePopulation {

    IController iController;

    public DatabasePopulation() {
        this.iController = new Controller();
    }

    public static void main(String[] args) {
        DatabasePopulation dp = new DatabasePopulation();

        SOSU sosu1 = new SOSU("Lars Jensen", "LJ");
        dp.iController.storeSOSU(sosu1, "ljpassword");

        Citizen cit1 = new Citizen("Jens", "Jens87", "190887-4050", java.sql.Date.valueOf("1987-08-19"));
        dp.iController.storeCitizen(cit1, "Jpassword", sosu1);

        for (int i = 1; i < 8; i++) {
            Activity a1 = new Activity("Exercise", "You need to stay healthy", 1200, 1400, i, "train.com\\pictogram.png");
            dp.iController.storeActivity(a1.getActivityID(), cit1.getId(), a1.getName(), a1.getDescription(),
                    a1.getStartTime(), a1.getEndTime(), a1.getDayOfTheWeek(), a1.getPictogramPath());
        }

        Activity a2 = new Activity("Shower", "You need to stay clean.", 1405, 1420, 1, "shower\\pictogram.png");
        dp.iController.storeActivity(a2.getActivityID(), cit1.getId(), a2.getName(), a2.getDescription(),
                a2.getStartTime(), a2.getEndTime(), a2.getDayOfTheWeek(), a2.getPictogramPath());
        
        Activity a3 = new Activity("Mealtime", "Ok", 1430, 1500, 1, "shower\\pictogram.png");
        dp.iController.storeActivity(a3.getActivityID(), cit1.getId(), a3.getName(), a3.getDescription(),
                a3.getStartTime(), a3.getEndTime(), a3.getDayOfTheWeek(), a3.getPictogramPath());

        Citizen cit2 = new Citizen("Jeppe", "Jeppe99", "120199-1234", java.sql.Date.valueOf("1999-01-12"));
        dp.iController.storeCitizen(cit2, "Jpassword", sosu1);

        for (int i = 1; i < 8; i++) {
            Activity a1 = new Activity("Say no.", "Don't do drugs!", 1000, 1600, i, "resources/sayNo.jpg");
            dp.iController.storeActivity(a1.getActivityID(), cit2.getId(), a1.getName(), a1.getDescription(),
                    a1.getStartTime(), a1.getEndTime(), a1.getDayOfTheWeek(), a1.getPictogramPath());
        }

    }

}
