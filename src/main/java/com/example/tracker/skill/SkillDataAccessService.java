package com.example.tracker.skill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class SkillDataAccessService implements SkillDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SkillDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Skill> selectAllSkills() {

        String sql = "" +
                "SELECT skill_id, " +
                "skill " +
                "FROM skills";

        return jdbcTemplate.query(sql, mapSkillFromDb());
    }

    private RowMapper<Skill> mapSkillFromDb() {
        return (resultSet, i) -> {
            String idStr = resultSet.getString("skill_id");
            UUID id = UUID.fromString(idStr);

            String skill = resultSet.getString("skill");

            return new Skill(
                    id,
                    skill
            );
        };
    }

    @Override
    public Optional<Skill> selectSkillById(UUID id) {

        final String sql = "SELECT skill_id, skill FROM skills WHERE skill_id = ?";

        Skill skill = jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (resultSet, i) -> {
                    UUID skillId = UUID.fromString(resultSet.getString("skill_id"));
                    String skillName = resultSet.getString("skill");
                    return new Skill(skillId, skillName);
                });

        return Optional.ofNullable(skill);
    }
}
