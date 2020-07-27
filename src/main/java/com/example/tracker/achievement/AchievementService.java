package com.example.tracker.achievement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AchievementService {

    private final AchievementDataAccessService achievementDataAccessService;

    @Autowired
    public AchievementService(AchievementDataAccessService achievementDataAccessService) {
        this.achievementDataAccessService = achievementDataAccessService;
    }

    public List<Achievement> getAchievements() {
        return achievementDataAccessService.selectAllAchievements();
    }

    void addAchievement(NewAchievement newAchievement) {
        addAchievement(null, newAchievement);
    }

    void addAchievement(UUID id, NewAchievement newAchievement) {
        UUID newAchievementId = Optional.ofNullable(id).orElse(UUID.randomUUID());

        achievementDataAccessService.insertAchievement(newAchievementId, newAchievement);
    }
}
