package com.dev.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertThat;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.notNullValue;

import com.dev.rest.dto.UserDTO;
import com.dev.rest.entities.User;
import com.dev.rest.entities.UserRole;
import com.dev.rest.errors.NoSuchEntityException;
import com.dev.rest.repositories.UserRepository;
import com.dev.rest.services.UserService;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserTest {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testFindByNameAndFindById() {
		User user = new User();
		user.setAge(20);
		user.setEmail("test");
		user.setEnabled(true);
		user.setName("testName");
		user.setPassword("password");
		user.setRole(UserRole.ROLE_USER);
		
		User u = userRepository.save(user);
		Integer id = u.getId();
		
		User foundById = userRepository.findOneByName("testName");
		
		assertThat(foundById, notNullValue());
		assertEquals(foundById.getId(), id);
		
		User foundByName = userRepository.findOneByName("testName");
		assertThat(foundByName, notNullValue());
		assertEquals(foundByName.getId(), id);
	}
	
	@Test
	public void testCreateUserAndDeleteUser() throws NoSuchEntityException {
		UserDTO dto = new UserDTO();
		dto.setAge(20);
		dto.setEmail("testMail");
		dto.setName("testCreate");
		dto.setPassword("password");
		UserDTO userCreated = userService.createUser(dto, UserRole.ROLE_USER);
		
		assertThat(userCreated, notNullValue());
		assertEquals(userCreated.getName(), "testCreate");
		
		userService.deleteUser(userCreated.getId());

	}
}
