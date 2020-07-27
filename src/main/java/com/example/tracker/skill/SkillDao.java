package com.example.tracker.skill;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SkillDao {

    List<Skill> selectAllSkills();

    Optional<Skill> selectSkillById(UUID id);

}
