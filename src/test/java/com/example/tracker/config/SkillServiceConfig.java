package com.example.tracker.config;

import com.example.tracker.skill.SkillService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class SkillServiceConfig {

    @Bean
    @Primary
    public SkillService nameService() {
        return Mockito.mock(SkillService.class);
    }
}
