package com.example.tracker.skill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

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

    private static Predicate<String> checkIfIsHotSkill = skill -> skill.length() > 5;

    public static boolean publicCheckIfIsHotSkill(String skill) {
        return checkIfIsHotSkill.test(skill);
    }


    void addSkill(Skill skill) {
        addSkill(null, skill, checkIfIsHotSkill.test(skill.getSkill()));
    }

    void addSkill(UUID id, Skill skill, Boolean isHotSkill) {
        UUID newSkillId = Optional.ofNullable(id).orElse(UUID.randomUUID());

        skillDataAccessService.insertSkill(newSkillId, skill, isHotSkill);
    }

    void deleteSkill(UUID id) {
        skillDataAccessService.deleteSkillById(id);
    }

}