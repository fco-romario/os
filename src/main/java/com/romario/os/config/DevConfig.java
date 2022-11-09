package com.romario.os.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.romario.os.service.BDService;

@Configuration
@Profile("dev")
public class DevConfig {
	
	@Autowired
	private BDService osService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String dll;
	
	@Bean
	public boolean run() {
		if(dll.equals("create")){
			this.osService.runBanco();
		}
		return false;
	}
}
