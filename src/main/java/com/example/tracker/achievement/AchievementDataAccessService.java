package com.example.tracker.achievement;

import com.example.tracker.profile.ProfileService;
import com.example.tracker.skill.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class AchievementDataAccessService implements AchievementDao {

    private final JdbcTemplate jdbcTemplate;
    private final ProfileService profileService;
    private final SkillService skillService;

    @Autowired
    public AchievementDataAccessService(JdbcTemplate jdbcTemplate,
                                        ProfileService profileService,
                                        SkillService skillService) {
        this.jdbcTemplate = jdbcTemplate;
        this.profileService = profileService;
        this.skillService = skillService;
    }

    @Override
    public List<Achievement> selectAllAchievements() {

        String sql = "" +
                "SELECT " +
                " achievements.achievement_id, " +
                " profiles.profile_id, " +
                " profiles.name, " +
                " skills.skill_id " +
                "FROM achievements " +
                "JOIN profiles USING (profile_id) " +
                "JOIN skills USING (skill_id) ";
        return jdbcTemplate.query(
                sql,
                mapAchievementFromDb()
        );
    }

    private RowMapper<Achievement> mapAchievementFromDb() {
        return (resultSet, i) ->
                new Achievement(
                        UUID.fromString(resultSet.getString("achievement_id")),
                        profileService.getProfileById(UUID.fromString(resultSet.getString("profile_id"))),
                        skillService.getSkillById(UUID.fromString(resultSet.getString("skill_id")))
                        );
    }

    @Override
    public int insertAchievement(UUID id, NewAchievement newAchievement) {

        String sql = "" +
                "INSERT INTO achievements (achievement_id, profile_id, skill_id) " +
                "VALUES (?, ?, ?)";

        return jdbcTemplate.update(
                sql,
                id,
                newAchievement.getProfile_id(),
                newAchievement.getSkill_id()
        );
    }
}
