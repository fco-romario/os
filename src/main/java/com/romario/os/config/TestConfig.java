package com.romario.os.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.romario.os.service.BDService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private BDService bdService;

	@Bean
	public void run() {
		this.bdService.runBanco();
	}
}
