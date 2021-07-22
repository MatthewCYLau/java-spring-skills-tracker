package com.example.tracker.integration;

import com.example.tracker.skill.SkillController;
import com.example.tracker.skill.SkillService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertEquals;


//@ContextConfiguration(classes = PasswordEncoder.class)
//@WebMvcTest(SkillController.class)
@SpringBootTest
@AutoConfigureMockMvc
class SkillControllerTest {

    @Autowired
    private MockMvc mockMvc;

//    @MockBean
    @Autowired
    private SkillService skillService;

//    returns 401 for unsecurted ULRs
//    see https://stackoverflow.com/questions/39554285/spring-test-returning-401-for-unsecured-urls
    @Test
    public void shouldReturnSkills() throws Exception {
        this.mockMvc.perform(get("/api/v1/skills")).andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldReturnSkillsFromService() throws Exception {
        assertEquals(skillService.getSkills(), "foo");
    }
}