package com.example.tracker.achievement;

import com.example.tracker.profile.Profile;
import com.example.tracker.skill.Skill;

import java.util.Optional;
import java.util.UUID;

public class Achievement {

    private final UUID id;
    private final Optional<Profile> profile;
    private final Optional<Skill> skill;

    public Achievement(UUID id, Optional<Profile> profile, Optional<Skill> skill) {
        this.id = id;
        this.profile = profile;
        this.skill = skill;
    }

    public UUID getId() {
        return id;
    }

    public Optional<Profile> getProfile() {
        return profile;
    }

    public Optional<Skill> getSkill() {
        return skill;
    }
}
