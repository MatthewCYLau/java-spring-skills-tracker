package com.example.tracker.skill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}