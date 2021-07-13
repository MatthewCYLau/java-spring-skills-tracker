package com.example.tracker;

import com.example.tracker.profile.ProfileController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class JavaSpringSkillsTrackerAppTests {

	@Autowired
	private ProfileController controller;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

	@Test
	public void exampleTest() {
		assertEquals("foo", "foo");
	}

}
