package com.dev.rest.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.dev.rest.dto.GroupDTO;
import com.dev.rest.errors.NoSuchEntityException;

public interface GroupService {
	
	List<GroupDTO> getAllGroups();
	List<GroupDTO> getAllGroupsByPage(Pageable pageble);
	GroupDTO groupInfo(String name);
	GroupDTO groupInfo(Integer id);
	GroupDTO createGroup(GroupDTO body);
	void deleteGroup(Integer id) throws NoSuchEntityException;
	void deleteGroup(String name) throws NoSuchEntityException;
}
