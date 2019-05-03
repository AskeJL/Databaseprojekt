package domain.users;

import java.io.Serializable;
import java.util.UUID;

public abstract class User implements Serializable {
  
    private final UUID id;
    private String name;
    private String username;

    public User(String name, String username) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

}
