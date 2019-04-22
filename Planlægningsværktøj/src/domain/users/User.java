package domain.users;

import java.io.Serializable;
import java.util.UUID;

public abstract class User implements Serializable {
  
    private UUID id;
    private String name;

    public User(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
