package com.example.tracker.jwt;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.crypto.SecretKey;

@Profile({"dev", "prod"})
@Configuration
public class JwtSecretKey {

	private final JwtConfig jwtConfig;

	@Autowired
	public JwtSecretKey(JwtConfig jwtConfig) {
		this.jwtConfig = jwtConfig;
	}

	@Bean
	public SecretKey secretKey() {
		return Keys.hmacShaKeyFor(jwtConfig.getSecretKey().getBytes());
	}

}