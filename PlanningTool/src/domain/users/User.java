package domain.users;

import java.util.UUID;

public abstract class User {

    private final UUID id;
    private String name;
    private String username;

    public User(String name, String username) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.username = username;
    }

    public User(String name, String username, UUID uuid) {
        this.id = uuid;
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
