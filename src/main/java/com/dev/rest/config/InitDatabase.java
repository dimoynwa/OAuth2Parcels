package com.dev.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.dev.rest.dto.UserDTO;
import com.dev.rest.entities.UserRole;
import com.dev.rest.services.UserService;

@Component
public class InitDatabase implements CommandLineRunner {

	@Autowired
	private UserService userService;
	
	@Override
	public void run(String... arg0) throws Exception {
		UserDTO dto = new UserDTO();
		dto.setName("admin");
		dto.setAge(23);
		dto.setEmail("testMail@host.bg");
		dto.setPassword("admin");
		
		userService.createUser(dto, UserRole.ROLE_ADMIN);
	}

}
