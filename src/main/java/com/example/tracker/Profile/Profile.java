package com.example.tracker.Profile;

import java.util.UUID;

public class Profile {

    private final UUID id;
    private final String name;
    private final String email;


    public Profile(UUID id,
                   String name,
                   String email) {
        this.id = id;
        this.name = name;
        this.email = email;

    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
