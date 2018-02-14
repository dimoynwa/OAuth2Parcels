package com.dev.rest.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.rest.dto.GroupDTO;
import com.dev.rest.entities.Group;
import com.dev.rest.entities.User;
import com.dev.rest.errors.NoSuchEntityException;
import com.dev.rest.repositories.GroupRepository;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupRepository groupRepository;
	
	@Override
	public List<GroupDTO> getAllGroups() {
		List<Group> allGroups = groupRepository.findAll();
		if(allGroups == null) {
			return new ArrayList<>();
		}
		return allGroups.stream().map(g -> g.toGeneralInfoDTO()).collect(Collectors.toList());
	}

	@Override
	public GroupDTO groupInfo(String name) {
		Group group = groupRepository.findOneByName(name);
		return group != null ? group.toDTO() : null;
	}

	@Override
	public GroupDTO groupInfo(Integer id) {
		Group group = groupRepository.findOneById(id);
		return group != null ? group.toDTO() : null;
	}

	@Override
	public GroupDTO createGroup(GroupDTO body) {
		Group gr = body.newGroup();
		gr = groupRepository.save(gr);
		body.setId(gr.getId());
		return body;
	}

	@Override
	public void deleteGroup(Integer id) throws NoSuchEntityException {
		Group group = groupRepository.findOne(id);
		for(User u : group.getUsers()) {
			u.removeFromGroup(group);
		}
		if(group == null) {
			throw new NoSuchEntityException("No such group for id : " + id);
		}
		groupRepository.deleteById(id);
	}

	@Override
	public void deleteGroup(String name) throws NoSuchEntityException {
		Group group = groupRepository.findOneByName(name);
		for(User u : group.getUsers()) {
			u.removeFromGroup(group);
		}
		if(group == null) {
			throw new NoSuchEntityException("No such group for name : " + name);
		}
		groupRepository.deleteByName(name);
	}

	@Override
	public List<GroupDTO> getAllGroupsByPage(Pageable pageble) {
		Page<Group> groups = groupRepository.findAll(pageble);
		return groups.getContent().stream().map(g -> g.toGeneralInfoDTO()).collect(Collectors.toList());
	}

}
