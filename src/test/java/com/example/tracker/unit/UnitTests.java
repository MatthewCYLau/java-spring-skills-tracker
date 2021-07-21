package com.example.tracker.unit;

import com.example.tracker.profile.ProfileController;
import com.example.tracker.skill.SkillService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UnitTests {

    @Autowired
    private ProfileController controller;
    private SkillService skillService;


    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void exampleTest() {
        assertEquals("foo", "foo");
    }


    @Test
    public void testIsHotSkill() {
        assertEquals(skillService.checkIfIsHotSkillPublic("foo"), false);
        assertEquals(skillService.checkIfIsHotSkillPublic("baaaaar"), true);
    }
}
