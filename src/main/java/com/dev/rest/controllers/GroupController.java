package com.dev.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev.rest.dto.GroupDTO;
import com.dev.rest.services.GroupService;

@RestController
@RequestMapping(value="/groups")
public class GroupController {

	@Autowired
	private GroupService groupService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public List<GroupDTO> getAllGroups() {
		return groupService.getAllGroups();
	}
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public List<GroupDTO> getAllGroupsByPage(Pageable pageble) {
		return groupService.getAllGroupsByPage(pageble);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public GroupDTO getById(@PathVariable(value="id") Integer id) {
		return groupService.groupInfo(id);
	}
}
