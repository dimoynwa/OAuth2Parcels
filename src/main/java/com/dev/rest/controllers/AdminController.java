package com.dev.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev.rest.dto.GroupDTO;
import com.dev.rest.dto.UserDTO;
import com.dev.rest.entities.UserRole;
import com.dev.rest.errors.NoSuchEntityException;
import com.dev.rest.services.GroupService;
import com.dev.rest.services.UserService;

@RestController
@RequestMapping(value="/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private GroupService groupService;
	
	@RequestMapping(value="/createUser", method=RequestMethod.POST)
	public UserDTO createUser(@RequestBody UserDTO body) {
		body = userService.createUser(body, UserRole.ROLE_USER);
		
		return body;
	}
	
	@RequestMapping(value="/createAdminUser", method=RequestMethod.POST)
	public UserDTO createAdminUser(@RequestBody UserDTO body) {
		body = userService.createUser(body, UserRole.ROLE_ADMIN);
		return body;
	}
	
	@RequestMapping(value="deleteUser", method=RequestMethod.DELETE)
	public void deleteUser(@RequestBody UserDTO body) throws NoSuchEntityException {
		if(body.getId() != null) {
			userService.deleteUser(body.getId());
		} else if(body.getName() != null) {
			userService.deleteUser(body.getName());
		} else {
			//TODO: Throw exception
		}
	}
	
	@RequestMapping(value="createGroup", method=RequestMethod.POST)
	public GroupDTO createGroup(@RequestBody GroupDTO dto) {
		return groupService.createGroup(dto);
	}
	
	@RequestMapping(value="deleteGroup", method=RequestMethod.DELETE)
	public void deleteGroup(@RequestBody GroupDTO body) throws NoSuchEntityException {
		if(body.getId() != null) {
			groupService.deleteGroup(body.getId());
		} else if(body.getName() != null) {
			groupService.deleteGroup(body.getName());
		} else {
			//TODO: Throw exception
		}
	}
	
}
