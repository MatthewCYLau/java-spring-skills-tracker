package com.example.tracker.achievement;

import java.util.UUID;

public class Achievement {

    private final UUID id;
    private final String name;
    private final String skill;


    public Achievement(UUID id, String name, String skill) {
        this.id = id;
        this.name = name;
        this.skill = skill;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSkill() {
        return skill;
    }
}
