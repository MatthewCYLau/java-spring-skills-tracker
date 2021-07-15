package com.example.tracker.skill;

import java.util.UUID;

public class Skill {

    private final UUID id;

    private final String skill;

    private final Boolean isHotSkill;

    public Skill(UUID id, String skill, Boolean isHotSkill) {
        this.id = id;
        this.skill = skill;
        this.isHotSkill = isHotSkill;

    }

    public UUID getId() {
        return id;
    }

    public String getSkill() {
        return skill;
    }

    public Boolean getHotSkill() {
        return isHotSkill;
    }

    @Override
    public String toString() {
        return "Skill{" + "id=" + id + ", skill='" + skill + '\'' + ", isHotSkill=" + isHotSkill + '}';
    }

}
