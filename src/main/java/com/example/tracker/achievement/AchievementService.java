package com.example.tracker.achievement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
