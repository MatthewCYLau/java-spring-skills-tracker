package com.example.tracker.skill;

import java.util.UUID;

public class Skill {

    private final UUID id;
    private final String skill;

    public Skill(UUID id,
                 String skill) {
        this.id = id;
        this.skill = skill;
    }

    public UUID getId() {
        return id;
    }

    public String getSkill() {
        return skill;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", skill='" + skill + '\'' +
                '}';
    }
}
