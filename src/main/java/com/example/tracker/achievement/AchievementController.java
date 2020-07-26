package com.example.tracker.achievement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/achievements")
public class AchievementController {

    private final AchievementService achievementService;

    @Autowired
    public AchievementController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    @GetMapping
    public List<Achievement> getAchievements() {
        return achievementService.getAchievements();
    }

    @PostMapping
    public ResponseEntity<Achievement> addAchievement(@RequestBody Achievement achievement) {
        achievementService.addAchievement(achievement);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
}
