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

		String sql = "" + "SELECT skill_id, " + "skill, " + "is_hot_skill " + "FROM skills";

		return jdbcTemplate.query(sql, mapSkillFromDb());
	}

	private RowMapper<Skill> mapSkillFromDb() {
		return (resultSet, i) -> {
			String idStr = resultSet.getString("skill_id");
			UUID id = UUID.fromString(idStr);

			String skill = resultSet.getString("skill");
			Boolean isHotSkill = resultSet.getBoolean("is_hot_skill");

			return new Skill(id, skill, isHotSkill);
		};
	}

	@Override
	public Optional<Skill> selectSkillById(UUID id) {

		final String sql = "SELECT skill_id, skill, is_hot_skill FROM skills WHERE skill_id = ?";

		Skill skill = jdbcTemplate.queryForObject(sql, new Object[] { id }, (resultSet, i) -> {
			UUID skillId = UUID.fromString(resultSet.getString("skill_id"));
			String skillName = resultSet.getString("skill");
			Boolean isHotSkill = resultSet.getBoolean("is_hot_skill");
			return new Skill(skillId, skillName, isHotSkill);
		});

		return Optional.ofNullable(skill);
	}

	@Override
	public int insertSkill(UUID id, Skill skill, Boolean is_hot_skill) {

		String sql = "" + "INSERT INTO skills (skill_id, skill, is_hot_skill) " + "VALUES (?, ?, ?)";

		return jdbcTemplate.update(sql, id, skill.getSkill(), is_hot_skill);
	}

	@Override
	public int deleteSkillById(UUID id) {
		String sql = "" + "DELETE FROM skills " + "WHERE skill_id = ?";
		return jdbcTemplate.update(sql, id);
	}

}
