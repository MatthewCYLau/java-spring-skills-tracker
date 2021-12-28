package com.example.tracker.integration;

import com.example.tracker.skill.Skill;
import com.example.tracker.skill.SkillService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class SkillControllerWithMockUserTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SkillService skillService;

    @Test
    @WithMockUser
    @DisplayName("Get skills")
    void getSkillsReturnSuccess() throws Exception {
        // given
        Skill skill = new Skill(UUID.fromString("e149b3dc-0552-11eb-adc1-0242ac120002"), "foo", false);
        List<Skill> skills = List.of(
                skill
        );

        //when
        when(skillService.getSkills()).thenReturn(skills);
        mockMvc.perform(get("/api/v1/skills")).andExpect(status().isOk());
    }
}
