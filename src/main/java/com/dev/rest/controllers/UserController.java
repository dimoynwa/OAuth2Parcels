package com.dev.rest.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.rest.dto.GroupDTO;
import com.dev.rest.dto.ParcelDTO;
import com.dev.rest.dto.UserDTO;
import com.dev.rest.errors.NoSuchEntityException;
import com.dev.rest.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public List<UserDTO> users() {
		return userService.getAllUsers();
	}
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public List<UserDTO> list(Pageable pageble) {
		return userService.getAllByPage(pageble);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public UserDTO getById(@PathVariable(value="id") Integer id) throws NoSuchEntityException {
		return userService.getById(id);
	}
	
	@RequestMapping(value="/joinGroup", method=RequestMethod.POST)
	public GroupDTO joinGroup(HttpServletRequest request, @RequestBody GroupDTO dto) throws NoSuchEntityException {
		String userName = request.getUserPrincipal().getName();
		return userService.joinGroup(userName, dto);
	}
	
	@RequestMapping(value="/leaveGroup", method=RequestMethod.POST)
	public GroupDTO leaveGroup(HttpServletRequest request, @RequestBody GroupDTO dto) throws NoSuchEntityException {
		String userName = request.getUserPrincipal().getName();
		return userService.leaveGroup(userName, dto);
	}
	
	@RequestMapping(value="/addParcel", method=RequestMethod.POST)
	public ParcelDTO addParcel(HttpServletRequest request, @RequestBody ParcelDTO dto) throws NoSuchEntityException {
		String userName = request.getUserPrincipal().getName();
		return userService.addParcel(userName, dto);
	}
	
	@RequestMapping(value="/deleteParcel", method=RequestMethod.POST)
	public ParcelDTO deleteParcel(HttpServletRequest request, @RequestBody ParcelDTO dto) throws NoSuchEntityException {
		String userName = request.getUserPrincipal().getName();
		return userService.deleteParcel(userName, dto);
	}
}
