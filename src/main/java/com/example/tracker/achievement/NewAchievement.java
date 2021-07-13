package com.example.tracker.achievement;

import java.util.UUID;

public class NewAchievement {

	private final UUID profile_id;

	private final UUID skill_id;

	public NewAchievement(UUID profile_id, UUID skill_id) {
		this.profile_id = profile_id;
		this.skill_id = skill_id;
	}

	public UUID getProfile_id() {
		return profile_id;
	}

	public UUID getSkill_id() {
		return skill_id;
	}

}
