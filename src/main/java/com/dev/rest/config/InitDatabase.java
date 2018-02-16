package com.dev.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.dev.rest.dto.UserDTO;
import com.dev.rest.entities.UserRole;
import com.dev.rest.services.UserService;

@Component
public class InitDatabase implements CommandLineRunner {

	@Value("${init.admin.username}")
	private String username;
	
	@Value("${init.admin.password}")
	private String password;
	
	@Autowired
	private UserService userService;
	
	@Override
	public void run(String... arg0) throws Exception {
		UserDTO dto = new UserDTO();
		dto.setName(username);
		dto.setAge(23);
		dto.setEmail("testMail@host.bg");
		dto.setPassword(password);
		
		userService.createUser(dto, UserRole.ROLE_ADMIN);
	}

}
