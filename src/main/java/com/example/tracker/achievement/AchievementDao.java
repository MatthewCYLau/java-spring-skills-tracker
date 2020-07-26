package com.example.tracker.achievement;

import java.util.List;
import java.util.UUID;

public interface AchievementDao {

    int insertAchievement(UUID id, Achievement achievement);

    List<Achievement> selectAllAchievements();
}
