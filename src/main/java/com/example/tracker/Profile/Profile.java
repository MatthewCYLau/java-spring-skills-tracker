package com.example.tracker.Profile;

public class Profile {

    private final Integer id;
    private final String name;

    public Profile(Integer id,
                   String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
