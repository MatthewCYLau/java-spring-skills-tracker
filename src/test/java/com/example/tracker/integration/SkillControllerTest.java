package com.example.tracker.integration;

import com.example.tracker.skill.SkillController;
import com.example.tracker.skill.SkillService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = PasswordEncoder.class)
@WebMvcTest(SkillController.class)
class SkillControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SkillService skillService;

//    returns 401 for unsecurted ULRs
//    see https://stackoverflow.com/questions/39554285/spring-test-returning-401-for-unsecured-urls
    @Test
    public void shouldReturnSkills() throws Exception {
        this.mockMvc.perform(get("/api/v1/skills")).andExpect(status().is4xxClientError());
    }
}