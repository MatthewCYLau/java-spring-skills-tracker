package com.example.tracker.skill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SkillService {
    private final SkillDataAccessService skillDataAccessService;

    @Autowired
    public SkillService(SkillDataAccessService skillDataAccessService) {
        this.skillDataAccessService = skillDataAccessService;
    }

    public List<Skill> getSkills() {
        return skillDataAccessService.selectAllSkills();
    }

    public Optional<Skill> getSkillById(UUID id) {
        return skillDataAccessService.selectSkillById(id);
    }

    void addSkill(Skill skill) {
        addSkill(null, skill);
    }

    void addSkill(UUID id, Skill skill) {
        UUID newSkillId = Optional.ofNullable(id).orElse(UUID.randomUUID());

        skillDataAccessService.insertSkill(newSkillId, skill);
    }

    void deleteSkill(UUID id) {
        skillDataAccessService.deleteSkillById(id);
    }
}