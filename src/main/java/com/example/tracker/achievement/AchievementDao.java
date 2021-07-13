package com.example.tracker.achievement;

import java.util.List;
import java.util.UUID;

public interface AchievementDao {

	int insertAchievement(UUID id, NewAchievement newAchievement);

	List<Achievement> selectAllAchievements();

}
