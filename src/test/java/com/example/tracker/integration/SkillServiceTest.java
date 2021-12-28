package com.example.tracker.integration;

import com.example.tracker.skill.Skill;
import com.example.tracker.skill.SkillDataAccessService;
import com.example.tracker.skill.SkillService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class SkillServiceTest {

    @Mock
    private SkillDataAccessService skillDataAccessService;

    private SkillService skillService;

    @BeforeEach
    void setUp() {
        skillService = new SkillService(
                skillDataAccessService
        );
    }

    @Test
    void shouldReturnFirstCallStatus() {
        // given
        Skill skill = new Skill(UUID.fromString("e149b3dc-0552-11eb-adc1-0242ac120002"), "foo", false);
        List<Skill> skills = List.of(
                skill
        );
        // when
        Mockito.when(skillDataAccessService.selectAllSkills()).thenReturn(skills);
        // then
        assertThat(skillService.getSkills()).isEqualTo(skills);
    }

}
