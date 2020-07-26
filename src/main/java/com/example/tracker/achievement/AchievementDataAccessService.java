package com.example.tracker.achievement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class AchievementDataAccessService implements AchievementDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AchievementDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Achievement> selectAllAchievements() {

        String sql = "" +
                "SELECT " +
                " achievements.achievement_id, " +
                " profiles.name, " +
                " skills.skill " +
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
                        resultSet.getString("name"),
                        resultSet.getString("skill")
                );
    }
}
