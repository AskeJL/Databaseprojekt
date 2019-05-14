package domain.users;

import java.util.ArrayList;
import java.util.UUID;

public class SOSU extends User {

    private ArrayList<Citizen> citizens;

    public SOSU(String name, String username) {
        super(name, username);
        citizens = new ArrayList<>();
    }
    
    public SOSU(String name, String username, UUID uuid) {
        super(name, username, uuid);
        citizens = new ArrayList<>();
    }

//    public SOSU(String name, String username, ArrayList<Citizen> citizens) {
//        super(name, username);
//        this.citizens = citizens;
//    }

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
    @Override
    public String toString(){
        return getName()+" "+getUsername();
    }
}
