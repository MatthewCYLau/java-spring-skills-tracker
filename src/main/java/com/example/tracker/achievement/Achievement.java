package com.example.tracker.achievement;

import java.util.UUID;

public class Achievement {

    private final UUID id;
    private final UUID profile_id;
    private final UUID skill_id;
    
    public Achievement(UUID id, UUID profile_id, UUID skill_id) {
        this.id = id;
        this.profile_id = profile_id;
        this.skill_id = skill_id;
    }

    public UUID getId() {
        return id;
    }

    public UUID getProfile_id() {
        return profile_id;
    }

    public UUID getSkill_id() {
        return skill_id;
    }
}
