package domain.users;

import java.util.ArrayList;

public class SOSU extends User {

    private ArrayList<Citizen> citizens;

    public SOSU(String name, String username) {
        super(name, username);
        citizens = new ArrayList<>();
    }

    public SOSU(String name, String username, ArrayList<Citizen> citizens) {
        super(name, username);
        this.citizens = citizens;
    }

    public ArrayList<Citizen> getCitizens() {
        return citizens;
    }

    public void addCitizen(Citizen citizen) {
        //TODO check for duplicates
        citizens.add(citizen);
    }

    public void removeCitizen(Citizen citizen) {
        //TODO check if exists
        citizens.remove(citizen);
    }
}
